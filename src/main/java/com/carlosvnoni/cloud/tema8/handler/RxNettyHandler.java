package com.carlosvnoni.cloud.tema8.handler;

import com.carlosvnoni.cloud.tema8.Calculator;
import com.carlosvnoni.cloud.tema8.Repository.Historic;
import com.carlosvnoni.cloud.tema8.configuration.Config;
import com.carlosvnoni.cloud.tema8.operations.Operation;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rx.Observable;


public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private final String healthCheckUri;
    private final HealthCheckEndpoint healthCheckEndpoint;

    @Autowired
    private final Calculator calculator;

    private final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
    private final Historic historic = (Historic) applicationContext.getBean("historic");

    public RxNettyHandler(String healthCheckUri, HealthCheckEndpoint healthCheckEndpoint, Calculator calculator) {
        this.healthCheckUri = healthCheckUri;
        this.healthCheckEndpoint = healthCheckEndpoint;
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        try {
            if (request.getUri().startsWith(healthCheckUri)) {
                return healthCheckEndpoint.handle(request, response);
            } else if (request.getUri().startsWith("/calculator")) {
                if (request.getQueryParameters().containsKey("firstnumber")
                        && request.getQueryParameters().containsKey("secondnumber")
                        && request.getQueryParameters().containsKey("operation")) {

                    double firstNumber = Double.parseDouble(request.getQueryParameters().get("firstnumber").get(0));
                    double secondNumber = Double.parseDouble(request.getQueryParameters().get("secondnumber").get(0));
                    String operation = request.getQueryParameters().get("operation").get(0);
                    operation = operation.toLowerCase();
                    Operation op = (Operation) applicationContext.getBean(operation);
                    double result = calculator.calculate(firstNumber, secondNumber, op);
                    historic.addOperation(firstNumber, op.getSimbol(), secondNumber, result);
                    response.setStatus(HttpResponseStatus.OK);
                    return response.writeStringAndFlush("The result is: " + Double.toString(result));
                }

            } else if (request.getUri().startsWith("/historic")) {

                return response.writeStringAndFlush(historic.getHistoricList().toString());
            }
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpResponseStatus.BAD_REQUEST);
            return response.writeStringAndFlush(e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            return response.writeStringAndFlush("INTERNAL SERVER ERROR");
        }
        response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
        return response.writeStringAndFlush("Invalid URL");
    }
}
