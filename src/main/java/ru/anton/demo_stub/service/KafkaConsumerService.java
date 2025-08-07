package ru.anton.demo_stub.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
//@Service
public class KafkaConsumerService { // Consumer читает сообщения по мере поступления
    private static final String TOPIC = "postedmessages";

    @KafkaListener(topics = TOPIC, groupId = "my-group")
    public void receiveMessage(String message) {
        // Process the received message
        System.out.println("Received message: " + message);
    }
}
//git@github.com:AntonSesin/Demo_Stub_Kafka.git