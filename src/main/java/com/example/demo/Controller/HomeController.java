package com.example.demo.Controller;

import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;
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
        request.setAttribute("role", user.getIs_admin(), WebRequest.SCOPE_SESSION);
    }

    @PostMapping("/login")
    public String login(WebRequest request, Model model) {
        String email = request.getParameter("mail");
        String password = request.getParameter("password");
        User user = userService.login(email, password);
        setSessionInfo(request, user);
        return "user/currentUser";
    }

}

