package ru.anton.demo_stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka        // Включаем работу с Кафкой
public class DemoStubApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoStubApplication.class, args);
    }

}
