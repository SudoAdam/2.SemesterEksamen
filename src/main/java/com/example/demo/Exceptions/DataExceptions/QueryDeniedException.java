/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Exceptions.DataExceptions;

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