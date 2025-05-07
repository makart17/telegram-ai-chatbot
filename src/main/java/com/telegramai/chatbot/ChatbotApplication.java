package com.telegramai.chatbot;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.telegramai.chatbot.bot.MyAiBot;

@SpringBootApplication
public class ChatbotApplication implements CommandLineRunner {

	private final MyAiBot myAiBot;

	public ChatbotApplication(MyAiBot myAiBot) {
		this.myAiBot = myAiBot;
	}

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Создаём API сессии и регистрируем нашего бота
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(myAiBot);
		System.out.println("✅ MyAiBot registered!");
	}
}
