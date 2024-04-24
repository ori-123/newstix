package com.example.newstixbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"models.user"})
@ComponentScan(basePackages = {"com.example.newstixbackend", "security", "util", "repository", "service", "models", "controller", "config"})
public class NewstixBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewstixBackendApplication.class, args);
    }

}
