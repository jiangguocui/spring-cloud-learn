package com.ejgc.cloudjaeger.controller;

import com.ejgc.cloudjaeger.annotation.OwlTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @OwlTrace
    @GetMapping
    public String hello() {
        return "hello world";
    }
}
