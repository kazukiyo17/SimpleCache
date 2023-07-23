package com.xyunli.cache;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleCacheApplication.class, args);
    }
}
