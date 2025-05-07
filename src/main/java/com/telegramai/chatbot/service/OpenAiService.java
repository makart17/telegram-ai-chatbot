package com.telegramai.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.http.HttpStatus;


import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService(@Value("${openai.api.key}") String apiKey) {
        // Build WebClient with base URL and default headers for OpenAI
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Sends the given prompt to OpenAI API and returns the generated response text.
     */
    public String askGpt(String prompt) {
        Map<String,Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role","user","content",prompt))
        );

        try {
            String json = webClient.post()
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return parseResponse(json);
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                // Когда нам вернули 429
                return "Sorry, there are too many requests for OpenAI right now - please try again in a minute.";
            }
            // для других ошибок можно пробросить или тоже вернуть текст
            return "An error occurred while contacting OpenAI: " + e.getStatusCode();
        }
    }


    /**
     * Parses the JSON string and extracts choices[0].message.content
     */
    private String parseResponse(String json) {
        try {
            JsonNode root = new ObjectMapper().readTree(json);
            return root
                    .path("choices").get(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing OpenAI response.";
        }
    }
}
