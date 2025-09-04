package ru.anton.demo_stub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.anton.demo_stub.model.MessageEntity;
import ru.anton.demo_stub.repository.MessageRepository;
import ru.anton.demo_stub.template.KafkaMessage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Slf4j // автоматически генерирует поле для работы с логами на основе SLF4J (Simple Logging Facade for Java).
@Component
@RequiredArgsConstructor
public class KafkaConsumerService { // Consumer читает сообщения по мере поступления

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String TOPIC = "postedmessages";

    @Autowired
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;

    @Value("${delay-ms}")
    @Getter
    private volatile long delayMs; // Значение по умолчанию: 1000 мс


    @KafkaListener(topics = TOPIC, groupId = "my-group")
    @Transactional
    public void receiveMessage(String message, Acknowledgment ack) {
        //log.info(message);
        logEvent("Read from Kafka", message);
        String s = new String(message);
        try {
            KafkaMessage kafkaMessage = objectMapper.readValue(message, KafkaMessage.class);
            MessageEntity entity = new MessageEntity(
                    kafkaMessage.getMsg_uuid(),
                    kafkaMessage.isHead(),
                    Instant.now().toEpochMilli()
            );

            Thread.sleep(delayMs);

            messageRepository.save(entity);
            logEvent("Write to DB", entity.toString());

        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
        ack.acknowledge();
    }

    private static void logEvent(String eventType, String content) {
        String timestamp = LocalDateTime.ofInstant(
                Instant.now(),
                ZoneId.systemDefault()
        ).format(formatter);

        System.out.println(
                timestamp + " – [" + eventType + "] " + content
        );
    }

    // Сеттер для обновления задержки
    public void setDelayMs(long delayMs) {
        logEvent("Изменение времени отклика: ", this.delayMs + "-->" + delayMs + "ms");
        this.delayMs = delayMs;

    }

}