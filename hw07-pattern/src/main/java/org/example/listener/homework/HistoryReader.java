package org.example.listener.homework;

import org.example.model.Message;

import java.util.Optional;

public interface HistoryReader {
    Optional<Message> findMessageById(long id);
}
