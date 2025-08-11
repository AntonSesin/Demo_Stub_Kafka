package ru.anton.demo_stub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anton.demo_stub.model.MessageEntity;
import ru.anton.demo_stub.repository.MessageRepository;
import ru.anton.demo_stub.template.KafkaMessage;

import java.util.concurrent.TimeUnit;


@Slf4j // автоматически генерирует поле для работы с логами на основе SLF4J (Simple Logging Facade for Java).
@Component
@RequiredArgsConstructor
public class KafkaConsumerService { // Consumer читает сообщения по мере поступления (для проверки)
    private static final String TOPIC = "postedmessages";

    @Autowired
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TOPIC, groupId = "my-group")
    public void receiveMessage(String message) {
        try {
            KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);

            MessageEntity entity = new MessageEntity(
                    kafkaMessage.getMsg_id(),
                    kafkaMessage.getTimestamp(),
                    kafkaMessage.getMethod(),
                    kafkaMessage.getUri()
            );

            messageRepository.save(entity);
            System.out.println("Message saved to DB: " + message);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}