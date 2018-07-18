package com.jgc.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcCOntroller {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-hi");
        String url = "http://" + serviceInstance.getHost()+":"+serviceInstance.getPort()+"/hi?name=consumer";
        System.out.println(url);

        return restTemplate.getForObject(url, String.class);
    }
}
