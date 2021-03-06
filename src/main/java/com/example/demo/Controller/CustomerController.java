/**
 * @Author Rasmus Berg and Adam
 */

package com.example.demo.Controller;

import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Project;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;
import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.LoginLogic;
import com.example.demo.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class CustomerController {
    CustomerService customerService;
    ProjectService projectService;
    LoginLogic loginLogic;

    public CustomerController(CustomerService customerService, ProjectService projectService, LoginLogic loginLogic) {
        this.customerService = customerService;
        this.projectService = projectService;
        this.loginLogic = loginLogic;
    }

    @GetMapping("/createCustomer")
    public String createProject(WebRequest request) throws LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            return "customer/createCustomer";
        }
    }

    @PostMapping("/createCustomer")
    public String createCustomer(WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String companyName = request.getParameter("comName");
            String contactName = request.getParameter("conName");
            String contactEmail = request.getParameter("conEmail");
            String tel = request.getParameter("tel");
            customerService.createCustomer(companyName, contactName, contactEmail, tel);
            return "redirect:/listCustomer";
        }
    }

    @GetMapping("/listCustomer")
    public String listCustomer(Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            ArrayList<Customer> customerList = customerService.getCustomers();
            model.addAttribute("customerList", customerList);
            return "customer/listCustomer";
        }
    }

    @RequestMapping(value = "/editCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String editCustomer(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            model.addAttribute("customer", customerService.getCustomer(id));
            return "customer/editCustomer";
        }
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            int customerID = Integer.parseInt(request.getParameter("cId"));
            String companyName = request.getParameter("comName");
            String contactName = request.getParameter("conName");
            String contactEmail = request.getParameter("conEmail");
            String tel = request.getParameter("tel");
            customerService.editCustomer(customerID, companyName, contactName, contactEmail, tel);
            return "redirect:/listCustomer";
        }
    }

    // Responds to /viewCustomer?id=project_id
    @RequestMapping(value = "/viewCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCustomer(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
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

    @RequestMapping(value = "/deleteCustomer", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCustomer(@RequestParam int id, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            customerService.deleteCustomer(id);
            return "redirect:/listCustomer";
        }
    }

    @PostMapping("/uploadCustomerImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String cID = request.getParameter("customer_id");
            int customer_id = Integer.parseInt(cID);
            customerService.addCustomerPicture(customer_id, file);
            return "redirect:/viewCustomer?id="+cID;
        }
    }
}
