package org.example.processor.homework;

import org.example.model.Message;
import org.example.processor.Processor;

public class ProcessorChangePlace implements Processor {

    @Override
    public Message process(Message message) {
        String msg = message.getField11();
        return message.toBuilder().field11(message.getField12()).field12(msg).build();
    }
}
