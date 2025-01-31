package com.augusto.kafkabasics.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> template;

    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    public void publishMessage(String topic, String key, String message) {
        template.send(topic, key, message);
        System.out.println("Message sent to Kafka topic: " + topic + " with key: " + key + " and message: " + message);
    }
}
