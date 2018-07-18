package com.jgc.eurekaconsumerribbonhystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ComsumerService {

    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer(){
        return restTemplate.getForObject("http://service-hi/hi?name=hystrix", String.class);
    }

    public String fallback(){
        return "fallback";
    }
}
