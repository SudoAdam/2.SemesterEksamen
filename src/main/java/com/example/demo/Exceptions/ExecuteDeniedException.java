package com.example.demo.Exceptions;

public class ExecuteDeniedException extends Throwable {
    private final String message;

    public ExecuteDeniedException(String message) {
        this.message = message;
    }

    public ExecuteDeniedException() {
        // Default message
        this.message = "Request could not be sent";
    }

    @Override
    public String getMessage() {
        return message;
    }
}