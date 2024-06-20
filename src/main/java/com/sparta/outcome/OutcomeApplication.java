package com.sparta.outcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// exclude={DataSourceAutoConfiguration.class}
@SpringBootApplication()
public class OutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeApplication.class, args);
    }

}
