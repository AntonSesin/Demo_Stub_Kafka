package ru.anton.demo_stub.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.DisconnectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.demo_stub.service.KafkaProducerService;
import ru.anton.demo_stub.template.KafkaMessage;
import ru.anton.demo_stub.template.MessageRequest;

@RestController
@RequestMapping
public class MessageController {

    //private static final String TOPIC = "postedmessages";

    @Autowired
    //private KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    private ObjectMapper objectMapper;

    public MessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/post-message")
    public ResponseEntity<String> postMessage(@RequestBody MessageRequest request) {

        try {
            // Создаем сообщение для Kafka
            KafkaMessage kafkaMessage = new KafkaMessage(
                    request.getMsg_id(),
                    System.currentTimeMillis(),
                    "POST",
                    "/post-message"
            );
            // Преобразуем в JSON
            String message = objectMapper.writeValueAsString(kafkaMessage);
            // Отправляем в Kafka
            //Thread.sleep(1000); // Типо операция выполняется
            kafkaProducerService.sendMessage(message);
            // kafkaTemplate.send(TOPIC, message);
            return ResponseEntity.ok("200 OK");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}