package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/login")
    public String login(WebRequest request){
       String email =  request.getParameter("mail");
       String password =  request.getParameter("password");
        userService.login(email, password);

        return "/viewUser";
    }

}

