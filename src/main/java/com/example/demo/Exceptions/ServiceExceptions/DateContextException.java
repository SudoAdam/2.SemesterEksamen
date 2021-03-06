/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Exceptions.ServiceExceptions;

public class DateContextException extends Exception {
    private final String message;

    public DateContextException() {
        // Default message
        this.message = "Start-date must be before end-date...";
    }

    public DateContextException(String message) {
        this.message = "DateContextException: " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}