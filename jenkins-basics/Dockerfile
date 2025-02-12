FROM eclipse-temurin:21-jdk-alpine
RUN mkdir /app
COPY ./build/libs/*.jar /app/calculator.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "calculator.jar"]