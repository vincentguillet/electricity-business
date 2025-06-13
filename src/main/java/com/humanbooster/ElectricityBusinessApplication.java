package com.humanbooster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElectricityBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricityBusinessApplication.class, args);

        System.out.println("Electricity Business Application has started successfully!");
    }
}
