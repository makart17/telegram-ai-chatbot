# Telegram AI Chatbot

This is a Spring Boot + Telegram Bots integration that forwards user messages to OpenAI’s GPT-3.5-turbo and returns the reply.

## Features

- Telegram long-polling bot
- Non-blocking WebClient to OpenAI API
- Retry with exponential backoff on HTTP 429
- Spring Boot Actuator (health, metrics)
- Clean architecture: `client` → `service` → `bot`

## Quickstart

1. Copy `application.properties.example` → `src/main/resources/application.properties`
2. Fill in your keys:
   ```properties
   telegram.bot.username=YOUR_BOT_USERNAME
   telegram.bot.token=YOUR_TELEGRAM_TOKEN
   openai.api.key=YOUR_OPENAI_API_KEY
3. Build & run:
   bash

     ./mvnw clean package
     java -jar target/chatbot-0.0.1-SNAPSHOT.jar
     Talk with your bot in Telegram!