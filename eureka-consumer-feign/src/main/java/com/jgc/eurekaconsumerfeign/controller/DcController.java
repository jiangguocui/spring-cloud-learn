package com.jgc.eurekaconsumerfeign.controller;

import com.jgc.eurekaconsumerfeign.service.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DcController {

    @Resource
    private DcClient dcClient;

    @GetMapping("/consumer")
    public String dc(){
        return dcClient.consumer("feign");
    }
}
