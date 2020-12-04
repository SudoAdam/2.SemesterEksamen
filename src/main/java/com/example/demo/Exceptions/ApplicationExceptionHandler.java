/**
 * class for handling exceptions across the whole application
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 04-12-2020
 */
package com.example.demo.Exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Model model, SQLException e) {
        model.addAttribute("exception", e.getClass());
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("exception", e.getClass());
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
