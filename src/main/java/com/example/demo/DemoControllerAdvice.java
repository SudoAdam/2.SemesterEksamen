/**
 * class for handling exceptions across the whole application
 *
 * @author Patrick Vincent Højstrøm
 * @since 04-12-2020
 */
package com.example.demo;

import com.example.demo.Exceptions.ServiceExceptions.DateContextException;
import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class DemoControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleLoginException() {
        return "authentication/login";
    }

    @ExceptionHandler(LoginException.class)
    public String handleLoginException(Model model, LoginException e) {
        model.addAttribute("LoginException", e.getMessage());
        return "authentication/login";
    }

    @ExceptionHandler(DateContextException.class)
    public String handleDateContextException(RedirectAttributes redirectAttributes, HttpServletRequest request, DateContextException e) {
        redirectAttributes.addFlashAttribute("DateContextException", e.getMessage());
        return "redirect:" + request.getHeader("referer");
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
