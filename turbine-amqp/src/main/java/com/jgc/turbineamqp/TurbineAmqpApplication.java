package com.jgc.turbineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableEurekaClient
@EnableTurbineStream
@SpringBootApplication
public class TurbineAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineAmqpApplication.class, args);
    }
}
