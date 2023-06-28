package org.example.processor.homework;

import java.time.LocalDateTime;

public class DateTimeImpl implements DateTimeConfig{

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
