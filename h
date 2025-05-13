warning: in the working copy of 'pom.xml', LF will be replaced by CRLF the next time Git touches it
warning: in the working copy of 'src/main/java/com/telegramai/chatbot/ChatbotApplication.java', LF will be replaced by CRLF the next time Git touches it
[1mdiff --git a/pom.xml b/pom.xml[m
[1mindex f62d4ec..25550da 100644[m
[1m--- a/pom.xml[m
[1m+++ b/pom.xml[m
[36m@@ -56,7 +56,11 @@[m
 			<scope>runtime</scope>[m
 			<optional>true</optional>[m
 		</dependency>[m
[31m-[m
[32m+[m		[32m<dependency>[m
[32m+[m			[32m<groupId>org.telegram</groupId>[m
[32m+[m			[32m<artifactId>telegrambots-spring-boot-starter</artifactId>[m
[32m+[m			[32m<version>6.9.7.1</version>[m
[32m+[m		[32m</dependency>[m
 		<dependency>[m
 			<groupId>org.springframework.boot</groupId>[m
 			<artifactId>spring-boot-starter-validation</artifactId>[m
[1mdiff --git a/src/main/java/com/telegramai/chatbot/ChatbotApplication.java b/src/main/java/com/telegramai/chatbot/ChatbotApplication.java[m
[1mindex 6a5d31b..2bbb6f7 100644[m
[1m--- a/src/main/java/com/telegramai/chatbot/ChatbotApplication.java[m
[1m+++ b/src/main/java/com/telegramai/chatbot/ChatbotApplication.java[m
[36m@@ -10,11 +10,13 @@[m [mimport org.telegram.telegrambots.meta.TelegramBotsApi;[m
 import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;[m
 import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;[m
 [m
[32m+[m
 @SpringBootApplication[m
 [m
 public class ChatbotApplication implements CommandLineRunner {[m
 	private final MyAiBot myAiBot;[m
[31m-	public ChatbotApplication(MyAiBot myAiBot) { this.myAiBot = myAiBot; }[m
[32m+[m	[32mpublic ChatbotApplication(MyAiBot myAiBot) {[m
[32m+[m		[32mthis.myAiBot = myAiBot; }[m
 [m
 	public static void main(String[] args) {[m
 		SpringApplication.run(ChatbotApplication.class, args);[m
[1mdiff --git a/src/main/resources/application.properties b/src/main/resources/application.properties[m
[1mindex e69de29..e93ed43 100644[m
[1m--- a/src/main/resources/application.properties[m
[1m+++ b/src/main/resources/application.properties[m
[36m@@ -0,0 +1,6 @@[m
[32m+[m[32m# src/main/resources/application.properties[m
[32m+[m[32mspring.config.import=optional:classpath:application.properties.local[m
[32m+[m[32m# src/test/resources/application.properties[m
[32m+[m[32mopenai.api.key=DUMMY_OPENAI_KEY[m
[32m+[m[32mtelegram.bot.username=DUMMY_USER[m
[32m+[m[32mtelegram.bot.token=DUMMY_TOKEN[m
