package com.augusto.kafkabasics.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.augusto.kafkabasics.configuration.KafkaConfig.KAFKA_BASICS_GROUP_ID;
import static com.augusto.kafkabasics.configuration.KafkaConfig.KAFKA_BASICS_TOPIC;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = KAFKA_BASICS_TOPIC, groupId = KAFKA_BASICS_GROUP_ID)
    public void consumerKafkaBasics(String event) {
        System.out.println("Message received from kafka: " + event);
    }

}
