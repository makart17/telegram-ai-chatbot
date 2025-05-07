package com.telegramai.chatbot.bot;

import com.telegramai.chatbot.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class MyAiBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(MyAiBot.class);

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    private final OpenAiService aiService;

    public MyAiBot(OpenAiService aiService) {
        this.aiService = aiService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        logger.debug(">> Received update: {}", update);
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        // Extract user message and chat ID
        String userText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        // Get response from OpenAI
        String reply = aiService.askGpt(userText);

        // Build and send the reply message
        SendMessage message = SendMessage.builder()
                .chatId(chatId.toString())
                .text(reply)
                .build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
