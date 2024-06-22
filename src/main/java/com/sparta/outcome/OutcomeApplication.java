package com.sparta.outcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// db 연결 잠시 미뤄둠 (exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class OutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeApplication.class, args);
    }

}
