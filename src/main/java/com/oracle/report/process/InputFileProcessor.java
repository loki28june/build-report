package com.oracle.report.process;

import com.oracle.report.reader.FileReader;
import com.oracle.report.writer.FileWriter;

public interface InputFileProcessor {
    void generateReport(String file);
    FileReader createFileParser();
    FileWriter createFileWriter();
}
