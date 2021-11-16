package com.scutcat.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpletestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpletestApplication.class, args);
    }

}
