# 1. Use a lightweight JDK 17 image for minimal footprint
FROM eclipse-temurin:17-jdk-alpine

# 2. Set working directory inside the container
WORKDIR /app

# 3. Copy Maven Wrapper and pom.xml to leverage layer caching
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 4. Pre-download all dependencies to speed up rebuilds
RUN chmod +x mvnw \
 && ./mvnw dependency:go-offline -B

# 5. Copy source code and build the application, skipping tests for faster builds
COPY src src
RUN ./mvnw clean package -DskipTests -B

# 6. Rename the built JAR to a consistent name
RUN mv target/chatbot-0.0.1-SNAPSHOT.jar app.jar

# 7. Document that the container listens on port 8080
EXPOSE 8080

# 8. Define entrypoint to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
