package com.augusto.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String RABBIT_BASICS_EXCHANGE = "rabbit-basics-exchange";
    public static final String RABBIT_BASICS_QUEUE = "rabbit-basics-queue";
    public static final String RABBIT_BASICS_ROUTING_KEY = "rabbit-basics";

    @Bean
    public Queue queue() {
        return new Queue(RABBIT_BASICS_QUEUE, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(RABBIT_BASICS_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(RABBIT_BASICS_ROUTING_KEY);
    }
}
