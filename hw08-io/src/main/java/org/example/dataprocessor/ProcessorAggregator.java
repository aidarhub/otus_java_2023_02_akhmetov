package org.example.dataprocessor;

import org.example.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        return new TreeMap<>(data.stream().collect(Collectors.toMap(Measurement::getName, Measurement::getValue, Double::sum)));
    }
}
