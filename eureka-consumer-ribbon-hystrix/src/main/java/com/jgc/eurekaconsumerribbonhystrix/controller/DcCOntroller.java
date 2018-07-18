package com.jgc.eurekaconsumerribbonhystrix.controller;

import com.jgc.eurekaconsumerribbonhystrix.service.ComsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DcCOntroller {

    @Resource
    private ComsumerService comsumerService;

    @GetMapping("/consumer")
    public String dc(){
        return comsumerService.consumer();
    }

}
