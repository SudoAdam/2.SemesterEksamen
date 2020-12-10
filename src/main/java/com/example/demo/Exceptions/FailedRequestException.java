package com.example.demo.Exceptions;

public class FailedRequestException extends Exception {
    private final String message;

    public FailedRequestException() {
        // Default message
        this.message = "FailedRequestException: service request failed";
    }

    public FailedRequestException(String message) {
        this.message = "FailedRequestException: " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}