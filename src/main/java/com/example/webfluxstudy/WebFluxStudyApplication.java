package com.example.webfluxstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@SpringBootApplication()
public class WebFluxStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxStudyApplication.class, args);
    }

}
