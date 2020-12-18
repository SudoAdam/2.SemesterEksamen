/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Exceptions.DataExceptions;

public class OperationDeniedException extends Exception {
    private final String message;

    public OperationDeniedException(String message) {
        this.message = message;
    }

    public OperationDeniedException() {
        // Default message
        this.message = "Request could not be performed";
    }

    @Override
    public String getMessage() {
        return message;
    }
}