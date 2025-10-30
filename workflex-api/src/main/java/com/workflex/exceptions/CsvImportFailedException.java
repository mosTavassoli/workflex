package com.workflex.exceptions;

public class CsvImportFailedException extends RuntimeException {
    public CsvImportFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvImportFailedException(String message) {
        super(message);
    }
}
