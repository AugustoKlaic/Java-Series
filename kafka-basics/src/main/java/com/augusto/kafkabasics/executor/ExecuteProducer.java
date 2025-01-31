package com.augusto.kafkabasics.executor;

import com.augusto.kafkabasics.producer.KafkaProducer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.augusto.kafkabasics.configuration.KafkaConfig.KAFKA_BASICS_TOPIC;

@Component
public class ExecuteProducer {

    private final KafkaProducer producer;

    public ExecuteProducer(KafkaProducer producer) {
        this.producer = producer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void publishMessage() throws InterruptedException {
        // This thread sleep is for the consumer to start before the message is sent
        Thread.sleep(5000);
        producer.publishMessage(KAFKA_BASICS_TOPIC, null, "hello World!");
    }
}
