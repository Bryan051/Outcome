package com.sparta.outcome.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sample {
    @RequestMapping("/")
    public String hello(){
        return "sample";
    }
}
