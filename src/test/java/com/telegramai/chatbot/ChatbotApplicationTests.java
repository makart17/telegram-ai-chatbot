package com.telegramai.chatbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ChatbotApplicationTests {

	// <-- Добавляем здесь
	@MockBean
	private CommandLineRunner commandLineRunner;
	// -->

	@Test
	void contextLoads() {
		// проверяем, что контекст стартует без ошибок
	}
}