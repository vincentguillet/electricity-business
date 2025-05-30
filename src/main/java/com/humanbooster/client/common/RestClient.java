package com.humanbooster.client.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String sendRequest(Method method, String url, String body) {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0");

        switch (method) {
            case GET -> builder.GET();
            case POST -> builder.POST(HttpRequest.BodyPublishers.ofString(body));
            case PUT -> builder.PUT(HttpRequest.BodyPublishers.ofString(body));
            case DELETE -> builder.DELETE();
            default -> throw new IllegalArgumentException("Invalid HTTP method: " + method);
        }

        HttpRequest request = builder.build();

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parseResponse(response.body());
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred while sending the request: " + e.getMessage());
        }

        return null;
    }

    private String parseResponse(String response) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(response);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            System.err.println("Error occurred while parsing the cars: " + e.getMessage());
        }
        return null;
    }
}
