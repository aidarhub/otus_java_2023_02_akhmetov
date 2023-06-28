package org.example.processor.homework;

import org.example.model.Message;
import org.example.processor.Processor;

import java.util.logging.Logger;

public class ProcessorException implements Processor {

    private static final Logger log = Logger.getLogger(ProcessorException.class.getName());
    private DateTimeConfig dateTimeConfig;

    public ProcessorException() {
    }

    public ProcessorException(DateTimeConfig dateTimeConfig) {
        this.dateTimeConfig = dateTimeConfig;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeConfig.now().getSecond() % 2 == 0) {
            log.info("Ошибка в четную секунду");
            throw new RuntimeException();
        }
        return message;
    }
}
