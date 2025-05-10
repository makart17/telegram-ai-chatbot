package com.telegramai.chatbot;

import com.telegramai.chatbot.bot.MyAiBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication

public class ChatbotApplication implements CommandLineRunner {
	private final MyAiBot myAiBot;
	public ChatbotApplication(MyAiBot myAiBot) { this.myAiBot = myAiBot; }

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(myAiBot);
		System.out.println("✅ MyAiBot registered!");
	}
}
