package ru.anton.demo_stub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Service
public class KafkaProducerService {
    private static final String TOPIC = "postedmessages";

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send(TOPIC, message);
    }
}
