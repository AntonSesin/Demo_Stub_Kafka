package ru.anton.demo_stub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.demo_stub.model.MessageEntity;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}