package com.jgc.jaegerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot";
    }


    @RequestMapping("/chaining")
    public String chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8181/hello", String.class);
        return "Chaining + " + response.getBody();
    }


}
