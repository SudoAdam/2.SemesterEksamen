package com.example.demo.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;


public class CustomerController {
    CustomerService customerService = new CustomerService();

    @GetMapping("/createCustomer")
    public String createProject() {
        return "project/createProject";
    }

    @PostMapping("/createCustomer")
    public String createProject(WebRequest request, Model model) {
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");



        return "customer/listCustomer";
    }

}
