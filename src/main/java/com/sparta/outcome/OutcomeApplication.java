package com.sparta.outcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// db 연결 잠시 미뤄둠
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeApplication.class, args);
    }

}
