package com.oracle.report.factory;

import com.oracle.report.constant.FileType;
import com.oracle.report.reader.CSVFileReader;
import com.oracle.report.reader.InputReader;

public class ReaderFactory {
    public static InputReader createReader(FileType fileType) {
        switch (fileType) {
            case CSV:
                return new CSVFileReader();
            case JSON:
                // return json reader implementation
            case XML:
                // return xml reader implementation
            default:
                throw new RuntimeException("Wrong user type passed.");
        }
    }
}
