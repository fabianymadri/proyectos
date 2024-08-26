package com.lucasian.pedirapi.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test", groupId = "test-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("mensaje recibido desde servidor kafka: " + record.value());
    }

}
