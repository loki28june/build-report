package com.oracle.report.exception;

public class FileNotValidException extends RuntimeException {
    public FileNotValidException(String file_is_invalid_or_empty) {
        super(file_is_invalid_or_empty);
    }
}
