# Stage 1: Build
FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src
RUN ./gradlew bootJar

# Stage 2: Run
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
