package org.example.dataprocessor;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Measurement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ResourcesFileLoader implements Loader {

    private final String fileName;
    private final ObjectMapper mapper;

    private record MeasurementData(String name, double value) {
    }

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        mapper = new ObjectMapper();
    }

    @Override
    public List<Measurement> load() throws IOException {
        //читает файл, парсит и возвращает результат
        try (var file = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (file == null) {
                throw new FileNotFoundException("File not found");
            }
            MappingIterator<MeasurementData> iterator = mapper.readerFor(MeasurementData.class).readValues(file);

            return iterator.readAll().stream().map(data -> new Measurement(data.name(), data.value())).collect(toList());
        }
    }
}
