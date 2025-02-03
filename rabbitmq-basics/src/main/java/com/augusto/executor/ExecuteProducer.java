package com.augusto.executor;

import com.augusto.producer.RabbitProducer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.augusto.configuration.RabbitMqConfiguration.RABBIT_BASICS_ROUTING_KEY;

@Component
public class ExecuteProducer {

    private final RabbitProducer producer;

    public ExecuteProducer(RabbitProducer producer) {
        this.producer = producer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void publishMessage() throws InterruptedException {
        // This thread sleep is for the consumer to start before the message is sent
        Thread.sleep(5000);
        producer.publishMessage("hello World!", RABBIT_BASICS_ROUTING_KEY);
    }
}
