/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Exceptions.ServiceExceptions;

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