package com.carlosvnoni.cloud.tema8.configuration;


import com.carlosvnoni.cloud.tema8.Calculator;
import com.carlosvnoni.cloud.tema8.Repository.Historic;
import com.carlosvnoni.cloud.tema8.handler.HealthcheckResource;
import com.carlosvnoni.cloud.tema8.operations.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.carlosvanoni.cloudnative.tema1")
public class Config {

    @Bean
    public Calculator calculator(){
        return new Calculator();
    }

    @Bean
    public Operation sum(){
        return new Addition();
    }

    @Bean
    public Operation sub(){
        return new Subtraction();
    }

    @Bean
    public Operation mult(){
        return new Multiplication();
    }

    @Bean
    public Operation div(){
        return new Division();
    }

    @Bean
    public Operation pow(){
        return new Exponentiation();
    }

    @Bean
    public Historic historic(){
        return new Historic();
    }

    @Bean
    public HealthcheckResource healthcheckResource() {
        return new HealthcheckResource();
    }

}