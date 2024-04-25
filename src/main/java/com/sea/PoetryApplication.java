package com.sea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableRetry
public class PoetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoetryApplication.class, args);
    }

}
