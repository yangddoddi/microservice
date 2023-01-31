package com.example.ecoomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EcoomerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoomerceApplication.class, args);
    }

}
