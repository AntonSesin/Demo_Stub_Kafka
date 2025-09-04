package ru.anton.demo_stub.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Объект для данных
 */
@Data   //Lombok: авто-генерация геттеров/сеттеров
@NoArgsConstructor // создаёт конструктор без аргументов.
@AllArgsConstructor // создаёт конструктор с аргументами для всех полей класса.
public class KafkaMessage {
    private String msg_uuid;     // ID сообщения
    private boolean head;
    private String method;     // HTTP-метод (POST/GET)
    private String uri;        // URI запроса
}