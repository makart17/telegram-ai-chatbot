# Telegram AI Chatbot

&#x20;

## 🚀 Project Overview

A Spring Boot-based Telegram bot that forwards user messages to OpenAI’s GPT‑3.5 turbo model and returns AI-generated replies. Ideal as a pet project to demonstrate:

* Spring Boot & WebFlux
* TelegramBots integration
* Reactive HTTP client (WebClient)
* CI/CD with GitHub Actions
* Code coverage reporting

## 📷 Demo

*Talk with your bot in Telegram!*

## 🛠️ Features

* ✅ Send/receive Telegram messages
* ✅ Throttling & retry on 429 responses
* ✅ Error handling & fallback messages
* ✅ Actuator endpoints (`/actuator/health`, `/actuator/metrics`)

## 📦 Prerequisites

* Java 17+
* Maven (`mvnw` wrapper included)
* Telegram Bot Token
* OpenAI API Key

## ⚙️ Configuration

1. Copy and rename `src/main/resources/application.properties.example` to `application.properties`:

   ```properties
   telegram.bot.username=YOUR_BOT_USERNAME
   telegram.bot.token=YOUR_TELEGRAM_TOKEN
   openai.api.key=YOUR_OPENAI_KEY
   ```
2. **Never commit** your real tokens to Git.

## 🚴‍♂️ Running Locally

```bash
# Clean & build
dotenv mvnw clean package

# Run
java -jar target/chatbot-0.0.1-SNAPSHOT.jar
```

## 📂 Project Structure

```
chatbot/
├── src/main/java/.../bot    # Telegram bot logic
├── src/main/java/.../client # OpenAI HTTP client
├── src/main/java/.../service# business service
├── src/main/resources      # application.properties
├── .github/workflows/ci.yml # CI definition
└── README.md               # this file
```

## 🛡️ CI & Code Coverage

* **GitHub Actions** runs `mvnw clean package` and tests on push & PR.
* **Coveralls** publishes coverage reports every build.

## 📚 Technologies

* Spring Boot 3.4.5
* Spring WebFlux
* TelegramBots Spring Starter
* Reactor Netty
* JUnit 5 & Mockito
* JaCoCo / Coveralls
* GitHub Actions

---

Made with 💜 by Artem Makarkin
