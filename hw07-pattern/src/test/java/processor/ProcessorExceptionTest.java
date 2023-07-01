package processor;

import org.example.model.Message;
import org.example.processor.homework.DateTimeConfig;
import org.example.processor.homework.ProcessorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProcessorExceptionTest {
    @Test
    @DisplayName("Проверка при четной секунде")
    public void getEvenSecondException() {
        var message = new Message.Builder(1L).field3("field3").build();
        var localDateTime = mock(DateTimeConfig.class);
        var processor = new ProcessorException(localDateTime);
        int sec = 2;
        when(localDateTime.now()).thenReturn(LocalDateTime.of(2023, 1, 1, 1, 1, sec));

        assertThrows(RuntimeException.class, () -> processor.process(message));
    }

    @Test
    @DisplayName("Проверка при нечетной секунде")
    public void getEvenSecondNotException() {
        var message = new Message.Builder(1L).field3("field3").build();
        var localDateTime = mock(DateTimeConfig.class);
        var processor = new ProcessorException(localDateTime);
        int sec = 1;
        when(localDateTime.now()).thenReturn(LocalDateTime.of(2023, 1, 1, 1, 1, sec));

        assertDoesNotThrow(() -> processor.process(message));
    }
}
