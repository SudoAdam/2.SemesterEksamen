package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome(){
        return "authentication/login";
    }

    @GetMapping("/register")
    public String register() { return "authentication/register";}

    @GetMapping("/employeeList")
    public String showEmployees() {
        return "lists/employeeList";
    }

    @GetMapping("/employeeEdit")
    public String editEmployee() {
        return "edit/employeeEdit";
    }
}

