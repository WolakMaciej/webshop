package com.example.webshopback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class WebshopbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopbackApplication.class, args);
    }

}
