package ru.anton.demo_stub.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Component
//@Service
public class KafkaConsumerService { // Consumer читает сообщения по мере поступления (для проверки)
    private static final String TOPIC = "postedmessages";

    @KafkaListener(topics = TOPIC, groupId = "my-group")
    public void receiveMessage(String message) throws InterruptedException {
        // Process the received message
        //Thread.sleep(10000); Имитация задержки чтения
        System.out.println("Received message: " + message);
    }
}