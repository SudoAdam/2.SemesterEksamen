package com.example.demo.Exceptions;

public class ExecutionDeniedException extends Exception {
    private final String message;

    public ExecutionDeniedException(String message) {
        this.message = message;
    }

    public ExecutionDeniedException() {
        // Default message
        this.message = "Request could not be sent";
    }

    @Override
    public String getMessage() {
        return message;
    }
}