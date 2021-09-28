package com.carlosvnoni.cloud.tema8;

import com.carlosvnoni.cloud.tema8.configuration.Config;
import com.carlosvnoni.cloud.tema8.handler.HealthcheckResource;
import com.carlosvnoni.cloud.tema8.handler.RxNettyHandler;
import io.reactivex.netty.RxNetty;
import netflix.karyon.health.HealthCheckHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KaryonRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        HealthCheckHandler healthCheck = (HealthcheckResource) applicationContext.getBean("healthcheckResource");

        RxNetty.createHttpServer(8090, new RxNettyHandler("/healthCheck",
                new HealthCheckEndpoint(healthCheck),
                (Calculator) applicationContext.getBean("calculator"))).startAndWait();
    }
}
