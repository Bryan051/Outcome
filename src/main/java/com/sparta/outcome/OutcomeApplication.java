package com.sparta.outcome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// security 인증 잠시 제외(exclude={DataSourceAutoConfiguration.class})
@EnableJpaAuditing
@SpringBootApplication
public class OutcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutcomeApplication.class, args);
    }

}
