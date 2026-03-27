FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /app
COPY ./build/libs/*.jar /app/secrets-app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "secrets-app.jar"]