package com.example.demo.Exceptions;

public class LoginException extends Throwable {
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
