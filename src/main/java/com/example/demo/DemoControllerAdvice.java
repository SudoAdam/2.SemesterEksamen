/**
 * class for handling exceptions across the whole application
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 04-12-2020
 */
package com.example.demo;

import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class DemoControllerAdvice {

    @ExceptionHandler(LoginException.class)
    public String handleLoginException() {
        return "redirect:/";
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
