package com.muzixiao2.bakabooru.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BackendDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDataApplication.class, args);
    }
}
