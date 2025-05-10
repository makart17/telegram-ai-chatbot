package com.telegramai.chatbot.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class OpenAiClientImpl implements OpenAiClient {

    private final WebClient webClient;

    public OpenAiClientImpl(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * Sends a chat completion request to OpenAI.
     * Retries up to 3 times on WebClientResponseException with exponential backoff.
     */
    @Override
    @Retryable(
            value = WebClientResponseException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public String askGpt(String prompt) {
        Map<String, Object> payload = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(Map.of("role", "user", "content", prompt))
        );

        String responseJson = webClient.post()
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .block();  // Blocking call within retryable context

        return parseResponse(responseJson);
    }

    /**
     * Fallback method invoked if askGpt still fails after retries.
     */
    @Recover
    public String recover(WebClientResponseException e, String prompt) {
        if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            return "Sorry, too many requests to OpenAI right now. Please try again in a minute.";
        }
        return "Failed to contact OpenAI: " + e.getStatusCode();
    }

    /**
     * Extracts the assistant's message content from the OpenAI JSON response.
     */
    private String parseResponse(String json) {
        try {
            var root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(json);
            return root.path("choices").get(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error parsing OpenAI response.";
        }
    }
}
