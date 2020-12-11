package com.example.demo.Exceptions;

public class DateContextException extends Exception {
    private final String message;

    public DateContextException() {
        // Default message
        this.message = "DateContextException: start-date must be before end-date";
    }

    public DateContextException(String message) {
        this.message = "DateContextException: " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}