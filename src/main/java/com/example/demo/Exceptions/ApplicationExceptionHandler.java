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
import java.util.Arrays;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Model model, SQLException e) {
        String title = "Database request error was encountered;";
        model.addAttribute("title", title);
        model.addAttribute("exception", e.getClass());
        model.addAttribute("message", e.getMessage());
        model.addAttribute("stacktrace", Arrays.toString(e.getStackTrace()));
        return "error";
    }

    @ExceptionHandler(LoginException.class)
    public String handleLoginException(Model model, LoginException e) {
        return "";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        String title = "Request failed error was encountered";
        model.addAttribute("title", title);
        model.addAttribute("exception", e.getClass());
        model.addAttribute("message", e.getMessage());
        model.addAttribute("stacktrace", Arrays.toString(e.getStackTrace()));
        return "error";
    }
}
