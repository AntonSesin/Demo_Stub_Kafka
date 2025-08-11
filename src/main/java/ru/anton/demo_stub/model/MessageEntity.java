package ru.anton.demo_stub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // создаёт конструктор без аргументов.
@AllArgsConstructor // создаёт конструктор с аргументами для всех полей класса.
@Entity
@Table(name = "kafka_messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // Автоинкрементный ID
    private String msg_id;
    private long timestamp;
    private String method;
    private String uri;

    public MessageEntity(String msg_id, long timestamp, String method, String uri) {
        this();
        this.msg_id = msg_id;
        this.timestamp = timestamp;
        this.method = method;
        this.uri = uri;
    }
}