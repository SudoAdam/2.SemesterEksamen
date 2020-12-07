package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.swing.*;

@Controller
public class HomeController {
    UserService userService = new UserService();

    @GetMapping("/")
    public String showHome() throws Exception {
        return "authentication/login";
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }

    public User getSessionInfo(WebRequest request){
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        return user;
    }

    @PostMapping("/login")
    public String login(WebRequest request, Model model) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        User user = userService.login(email, password);
        setSessionInfo(request, user);
        User currentUser = (User) request.getAttribute("user" ,WebRequest.SCOPE_SESSION);
        model.addAttribute("user", currentUser);
        return "user/currentUser";
    }

    @GetMapping("/logout")
    public String logout(WebRequest request){
        // en hurtig tanke... Ser ud til at virke. men skal testet godt igennem
        request.removeAttribute("user",WebRequest.SCOPE_SESSION);
        return "authentication/login";
    }

    @GetMapping("/loggedin")
    public String loggedin(WebRequest request,Model model){
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (user != null) {
            return "authentication/loggedin";
        } else
            return "redirect:/";
    }
}

