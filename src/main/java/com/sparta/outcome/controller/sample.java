package com.sparta.outcome.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sample {
    @GetMapping("/sample")
    public String hello(){
        return "sample";
    }
}
