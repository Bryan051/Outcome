package com.sparta.outcome.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class sample {
    @RequestMapping("/hello")
    public String hello(){
        return "sample";
    }
}
