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
    @Column(name = "msgUuid")
    private String msg_uuid;
    private boolean head;
    private long timeRq;


    public MessageEntity(String msg_uuid, boolean head, long timeRq) {
        this();
        this.msg_uuid = msg_uuid;
        this.head = head;
        this.timeRq = timeRq;
    }

    @Override
    public String toString() {
        return "{ \"msgUuid\": \"" + msg_uuid + "\", \"head\": " + head + ", \"timeRq\": \"" + timeRq + "\" }";
    }
}