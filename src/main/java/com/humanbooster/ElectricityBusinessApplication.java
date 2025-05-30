package com.humanbooster;

import com.humanbooster.client.common.Method;
import com.humanbooster.client.common.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElectricityBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectricityBusinessApplication.class, args);

        System.out.println("Electricity Business Application has started successfully!");

        RestClient client = new RestClient();

        String utilisateurs = client.sendRequest(Method.GET, "http://localhost:8080/utilisateurs", null);
    }

}
