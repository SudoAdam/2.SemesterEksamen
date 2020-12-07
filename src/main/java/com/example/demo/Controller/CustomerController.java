/**
 @Author Rasmus Berg and Adam
 */


package com.example.demo.Controller;

import com.example.demo.DemoConfiguration;
import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Project;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.ProjectService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class CustomerController {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DemoConfiguration.class);
    CustomerService customerService = (CustomerService) ctx.getBean("customerService");
    ProjectService projectService = (ProjectService) ctx.getBean("projectService");


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

        customerService.createUser(companyName, contactName, contactEmail, tel);

        return "customer/listCustomer";
    }

    @GetMapping("/listCustomer")
    public String listCustomer(Model model) {

        ArrayList<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList", customerList);
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

    // Responds to /viewCustomer?id=project_id
    @RequestMapping(value = "/viewCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCustomer(@RequestParam int id, Model model) throws SQLException {

        ArrayList<Project> projectList = projectService.getProjects();
        ArrayList<Project> projectCustomerList = new ArrayList<>();
        for ( Project p : projectList) {
            if (p.getCustomer_id() == id)
            projectCustomerList.add(p);
        }
        model.addAttribute("customer", customerService.getCustomer(id));
        model.addAttribute("customerProjects", projectCustomerList);
        return "customer/viewCustomer";

    }

}
