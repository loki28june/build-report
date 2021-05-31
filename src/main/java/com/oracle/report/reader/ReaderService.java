package com.oracle.report.reader;

import com.oracle.report.model.InputData;

import java.util.List;

public class ReaderService {
    private InputReader inputReader;

    public List<InputData> performRead() {
        return inputReader.read();
    }

    public InputReader getInputReader() {
        return inputReader;
    }

    public void setInputReader(InputReader inputReader) {
        this.inputReader = inputReader;
    }
}
