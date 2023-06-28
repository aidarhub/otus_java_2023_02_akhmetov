package org.example.listener.homework;

import org.example.listener.Listener;
import org.example.model.Message;
import org.example.model.ObjectForMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    Map<Long, Message> messageMap = new HashMap<>();

    public HistoryListener() {
    }

    @Override
    public void onUpdated(Message msg) {
        Message copy = msg.toBuilder().build();
        List<String> data = List.copyOf(copy.getField13().getData());
        ObjectForMessage objectForMessage = new ObjectForMessage();
        objectForMessage.setData(data);
        messageMap.put(copy.getId(), copy.toBuilder().field13(objectForMessage).build());
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.ofNullable(messageMap.get(id));
    }
}
