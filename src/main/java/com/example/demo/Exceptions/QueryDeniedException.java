package com.example.demo.Exceptions;

public class QueryDeniedException extends Exception {
    private final String message;

    public QueryDeniedException(String message) {
        this.message = message;
    }

    public QueryDeniedException() {
        // Default message
        this.message = "Request could not be received";
    }

    @Override
    public String getMessage() {
        return message;
    }
}