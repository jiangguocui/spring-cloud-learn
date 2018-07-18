package com.jgc.jaegerdemo;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication
@RestController

public class JaegerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaegerDemoApplication.class, args);
    }

    @Bean
    public io.opentracing.Tracer jaegerTracer(){
        return new Configuration("spring-bot", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
                new Configuration.ReporterConfiguration()
                ).getTracer();
    }


    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        return "Hello from Spring Boot";
    }


    @RequestMapping("/chaining")
    public String chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/hello", String.class);
        return "Chaining + " + response.getBody();
    }


}
