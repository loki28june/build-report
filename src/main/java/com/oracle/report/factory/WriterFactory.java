package com.oracle.report.factory;

import com.oracle.report.constant.FileType;
import com.oracle.report.reader.CSVFileReader;
import com.oracle.report.reader.InputReader;
import com.oracle.report.writer.CsvFileWriter;
import com.oracle.report.writer.OutputWriter;

public class WriterFactory {
    public static OutputWriter createWriter(FileType fileType) {
        switch (fileType) {
            case CSV:
                return new CsvFileWriter();
            case JSON:
                // return json reader implementation
            case XML:
                // return xml reader implementation
            default:
                throw new RuntimeException("Wrong user type passed.");
        }
    }
}
