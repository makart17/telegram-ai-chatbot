package com.telegramai.chatbot.service;

import org.springframework.stereotype.Service;
import com.telegramai.chatbot.client.OpenAiClient;

@Service
public class OpenAiService {

    private final OpenAiClient client;

    public OpenAiService(OpenAiClient client) {
        this.client = client;
    }

    public String askGpt(String prompt) {
        return client.askGpt(prompt);
    }
}
