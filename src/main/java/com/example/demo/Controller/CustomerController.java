/**
 * @Author Rasmus Berg and Adam
 */

package com.example.demo.Controller;

import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Project;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.ProjectService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class CustomerController {
    CustomerService customerService;
    ProjectService projectService;

    public CustomerController(CustomerService customerService, ProjectService projectService) {
        this.customerService = customerService;
        this.projectService = projectService;
    }

    @GetMapping("/createCustomer")
    public String createProject() {
        return "customer/createCustomer";
    }

    @PostMapping("/createCustomer")
    public String createCustomer(WebRequest request, Model model) throws SQLException {
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String tel = request.getParameter("tel");
        customerService.createCustomer(companyName, contactName, contactEmail, tel);
        return "customer/listCustomer";
    }

    @GetMapping("/listCustomer")
    public String listCustomer(Model model) throws SQLException {
        ArrayList<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList", customerList);
        return "customer/listCustomer";
    }

    @RequestMapping(value = "/editCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String editCustomer(@RequestParam int id, Model model) throws SQLException {
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customer/editCustomer";
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(WebRequest request, Model model) throws SQLException {
        int customerID =Integer.parseInt(request.getParameter("cId"));
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String tel = request.getParameter("tel");
        customerService.editCustomer(customerID, companyName, contactName, contactEmail, tel);
        return "redirect:/listCustomer";
    }

    // Responds to /viewCustomer?id=project_id
    @RequestMapping(value = "/viewCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCustomer(@RequestParam int id, Model model) throws SQLException {
        ArrayList<Project> projectList = projectService.getProjects();
        ArrayList<Project> projectCustomerList = new ArrayList<>();
        for (Project p : projectList) {
            if (p.getCustomer_id() == id)
                projectCustomerList.add(p);
        }
        model.addAttribute("customer", customerService.getCustomer(id));
        model.addAttribute("customerProjects", projectCustomerList);
        return "customer/viewCustomer";

    }

}
