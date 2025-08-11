package ru.anton.demo_stub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.anton.demo_stub.template.KafkaMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "postedmessages";
    private final KafkaTemplate<String, String> kafkaTemplate; // Типизированная отправка

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }


}
