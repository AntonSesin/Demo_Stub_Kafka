package ru.anton.demo_stub.template;

import lombok.Data;

/**
 * Объект для входящих запросов
 */
@Data
public class MessageRequest {
    private String msg_id;
}
