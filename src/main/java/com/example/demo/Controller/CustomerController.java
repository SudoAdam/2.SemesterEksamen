/**
 @Author Rasmus Berg and Adam
 */


package com.example.demo.Controller;

import com.example.demo.Domain.Customer;
import com.example.demo.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {
    CustomerService customerService = new CustomerService();

    @GetMapping("/createCustomer")
    public String createProject() {
        return "customer/createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createProject(WebRequest request, Model model) {
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String tel = request.getParameter("tel");


        return "customer/listCustomer";
    }

    @GetMapping("/listCustomer")
    public String listCustomer(Model model) {
        ArrayList<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList",customerList);
        return "customer/listCustomer";
    }

    @GetMapping("/editCustomer")
    public String editCustomer() {
        return "customer/editCustomer";
    }

    @PostMapping("/editCustomer")
    public String editProject(WebRequest request, Model model) {
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String tel = request.getParameter("tel");


        return "customer/listCustomer";
    }

}
