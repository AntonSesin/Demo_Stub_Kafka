package ru.anton.demo_stub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@Entity
@Table(name = "kafka_messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "method")
    private String method;

    @Column(name = "uri")
    private String uri;

    @Column(name = "created_at")
    private Date createdAt;

    // Конструкторы, геттеры и сеттеры
    public MessageEntity() {
        this.createdAt = Date.from(Instant.now());
    }

    public MessageEntity(String messageId, long timestamp, String method, String uri) {
        this();
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.method = method;
        this.uri = uri;
    }
}