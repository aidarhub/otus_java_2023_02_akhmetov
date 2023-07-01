package org.example;

import org.example.hadler.ComplexProcessor;
import org.example.listener.homework.HistoryListener;
import org.example.model.Message;
import org.example.model.ObjectForMessage;
import org.example.processor.LoggerProcessor;
import org.example.processor.ProcessorConcatFields;
import org.example.processor.ProcessorUpperField10;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {
     /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
             Секунда должна определяьться во время выполнения.
             Тест - важная часть задания
             Обязательно посмотрите пример к паттерну Мементо!
       4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
          Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
          Для него уже есть тест, убедитесь, что тест проходит
     */

    public static void main(String[] args) {
       /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()));

        var complexProcessor = new ComplexProcessor(processors, ex -> {});
        var listener = new HistoryListener();
        complexProcessor.addListener(listener);

        var message = new Message.Builder(1L)
                .field9("field9")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(new ObjectForMessage())
                .build();

        message.getField13().setData(new ArrayList<>());
        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listener);
    }
}