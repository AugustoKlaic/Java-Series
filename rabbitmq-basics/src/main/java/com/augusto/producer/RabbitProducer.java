package com.augusto.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.augusto.configuration.RabbitMqConfiguration.RABBIT_BASICS_EXCHANGE;

@Component
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(RABBIT_BASICS_EXCHANGE, routingKey, message);
        System.out.println("Message sent to Rabbit exchange: " + RABBIT_BASICS_EXCHANGE + " using routing key: " + routingKey + " and message: " + message);
    }
}
