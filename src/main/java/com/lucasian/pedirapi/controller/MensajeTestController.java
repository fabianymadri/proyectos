package com.lucasian.pedirapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/messages")
public class MensajeTestController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "test";

    @PostMapping
    public String sendMessage(@RequestParam("message") String message) {
        kafkaTemplate.send(TOPIC, message);
        return "mensaje enviado a Kafka topic " + TOPIC;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "prueba de microservicio";
    }
}
