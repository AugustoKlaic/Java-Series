package com.augusto.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.augusto.configuration.RabbitMqConfiguration.RABBIT_BASICS_QUEUE;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = RABBIT_BASICS_QUEUE)
    public void consumerRabbitBasics(String message) {
        System.out.println("Message received from rabbit: " + message);
    }
}
