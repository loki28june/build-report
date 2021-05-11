package com.oracle.report.process;

import com.oracle.report.reader.CSVFileReader;
import com.oracle.report.reader.FileReader;
import com.oracle.report.writer.CsvFileWriter;
import com.oracle.report.writer.FileWriter;

/**
 * This class processes the csv file provided as a input.
 */
public class CSVFileProcessor implements InputFileProcessor{

    @Override
    public void generateReport(String file) {
        FileReader fileReader = createFileParser();
        fileReader.read(file);
        FileWriter fileWriter = createFileWriter();
        fileWriter.write();
    }

    @Override
    public FileReader createFileParser() {
        return new CSVFileReader();
    }

    @Override
    public FileWriter createFileWriter() {
        return new CsvFileWriter();
    }
}
