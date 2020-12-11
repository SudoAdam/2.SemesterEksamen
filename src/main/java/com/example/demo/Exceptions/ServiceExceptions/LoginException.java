package com.example.demo.Exceptions.ServiceExceptions;

public class LoginException extends Exception {
    private final String message;

    public LoginException(String message) {
        this.message = message;
    }

    public LoginException() {
        this.message = "";
    }

    @Override
    public String getMessage() {
        return message;
    }
}