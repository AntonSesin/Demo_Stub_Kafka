package ru.anton.demo_stub.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anton.demo_stub.service.KafkaConsumerService;

@RestController
@RequestMapping
@RequiredArgsConstructor // Lombok: инъекция зависимостей через конструктор
public class DelayController {

    @Autowired
    private final KafkaConsumerService kafkaConsumerService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/delay")
    public String setDelay(@RequestParam long delayMs) {
        kafkaConsumerService.setDelayMs(delayMs);
        return "Время отклика изменено на " + delayMs + " ms";
    }

    @GetMapping("/delay")
    public long getDelay() {
        return kafkaConsumerService.getDelayMs();
    }

}