package ru.anton.demo_stub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.anton.demo_stub.model.MessageEntity;
import ru.anton.demo_stub.repository.MessageRepository;
import ru.anton.demo_stub.template.KafkaMessage;

import java.util.concurrent.TimeUnit;


@Component
//@Service
public class KafkaConsumerService { // Consumer читает сообщения по мере поступления (для проверки)
    private static final String TOPIC = "postedmessages";

    @Autowired
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;


    public KafkaConsumerService(MessageRepository messageRepository, ObjectMapper objectMapper) {
        this.messageRepository = messageRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = TOPIC, groupId = "my-group")
    public void receiveMessage(String message) throws InterruptedException {
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

        //Thread.sleep(10000); Имитация задержки чтения

        /*System.out.println("Received message: " + message);
        MessageEntity entity = new MessageEntity("1",1,"1","1");
        messageRepository.save(entity);*/
    }
}