package com.jgc.eurekaconsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-hi")
public interface DcClient {

    @GetMapping("/hi")
    String consumer(@RequestParam(value = "name") String name);
}
