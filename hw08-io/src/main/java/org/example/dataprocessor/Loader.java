package org.example.dataprocessor;

import org.example.model.Measurement;

import java.io.IOException;
import java.util.List;

public interface Loader {

    List<Measurement> load() throws IOException;
}
