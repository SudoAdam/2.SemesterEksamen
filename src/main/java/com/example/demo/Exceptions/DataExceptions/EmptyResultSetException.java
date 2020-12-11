package com.example.demo.Exceptions.DataExceptions;

public class EmptyResultSetException extends Exception {
    private final String message;

    public EmptyResultSetException() {
        // Default message
        this.message = "EmptyResultSetException: ResultSet was empty during operation";
    }

    @Override
    public String getMessage() {
        return message;
    }
}