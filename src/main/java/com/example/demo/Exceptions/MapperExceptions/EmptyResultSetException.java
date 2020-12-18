/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Exceptions.MapperExceptions;

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