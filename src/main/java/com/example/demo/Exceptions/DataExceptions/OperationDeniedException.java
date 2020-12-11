package com.example.demo.Exceptions.DataExceptions;

public class OperationDeniedException extends Exception {
    private final String message;

    public OperationDeniedException(String message) {
        this.message = message;
    }

    public OperationDeniedException() {
        // Default message
        this.message = "Request could not be sent";
    }

    @Override
    public String getMessage() {
        return message;
    }
}