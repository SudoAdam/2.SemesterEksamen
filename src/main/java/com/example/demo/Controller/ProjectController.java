/**
 * @author Kasper Fauerby
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Controller;

import com.example.demo.Domain.Project;
import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ServiceExceptions.DateContextException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;
import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import com.example.demo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    ProjectService projectService;
    TaskService taskService;
    UserService userService;
    CustomerService customerService;
    LoginLogic loginLogic;

    public ProjectController(ProjectService projectService, TaskService taskService, UserService userService, CustomerService customerService, LoginLogic loginLogic) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.customerService = customerService;
        this.loginLogic = loginLogic;
    }

    @GetMapping("/listProject")
    public String showProjects(Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

            model.addAttribute("projectList", projectService.getProjects());
            model.addAttribute("userProjectList", projectService.getUserProjects(user.getUser_id()));
            return "project/listProject";
        }
    }

    @GetMapping("/createProject")
    public String createProject(Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            try {
                model.addAttribute(model.getAttribute("DateContextException"));
            } catch (Exception e) {
                assert(true);
            }
            model.addAttribute("customers", customerService.getCustomers());
            model.addAttribute("users", userService.getUsers());
            return "project/createProject";
        }
    }

    @PostMapping("/createProject")
    public String createProject(WebRequest request) throws FailedRequestException, DateContextException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String projectName = request.getParameter("pName");
            String kickOffStr = request.getParameter("kickOff");
            String deadlineStr = request.getParameter("deadline");
            String pLId = request.getParameter("pLId");
            String cId = request.getParameter("cId");

            int project_leader_id = Integer.parseInt(pLId);                                  //request email and get project_leader_id
            int customer_id = Integer.parseInt(cId);
            LocalDate kickOff = LocalDate.parse(kickOffStr);
            LocalDate deadline = LocalDate.parse(deadlineStr);

            projectService.createProject(projectName, kickOff, deadline, project_leader_id, customer_id);
            return "redirect:/listProject";
        }
    }

    // Responds to /editProject?id=project_id
    @RequestMapping(value = "/editProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String editProject(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            Project p = projectService.getProject(id);
            model.addAttribute("customers", customerService.getCustomers());
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("projectmanager", userService.getUser(p.getProject_leader_id()));
            model.addAttribute("currentcustomer", customerService.getCustomer(p.getCustomer_id()));
            model.addAttribute("project", p);
            return "project/editProject";
        }
    }

    @PostMapping("/updateProject")
    public String updateProject(WebRequest request, Model model) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String projectId = request.getParameter("id");
            String projectName = request.getParameter("pName");
            String kickOffStr = request.getParameter("kickOff");
            String deadlineStr = request.getParameter("deadline");
            String pLeaderId = request.getParameter("plId");
            String CustomerId = request.getParameter("cId");

            int pId = Integer.parseInt(projectId);
            LocalDate kickoff = LocalDate.parse(kickOffStr);
            LocalDate deadline = LocalDate.parse(deadlineStr);
            int pLId = Integer.parseInt(pLeaderId);
            int cId = Integer.parseInt(CustomerId);

            projectService.editProject(pId, projectName, kickoff, deadline, pLId, cId);
            model.addAttribute("project", projectService.getProject(pId));
            model.addAttribute("participants", projectService.getParticipants(pId));

            return "redirect:/viewProject?id=" + pId;
        }
    }

    // Responds to /viewProject?id=project_id
    @RequestMapping(value = "/viewProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewProject(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            ArrayList<Task> tasks = taskService.getTasks(id);
            ArrayList<SubTask> subTasks = taskService.getSubTasks(id);
            tasks = taskService.getTaskHours(tasks,subTasks);
            Project p = projectService.getProject(id);
            model.addAttribute("participants",projectService.getParticipants(id));
            model.addAttribute("subtasks", subTasks);
            model.addAttribute("projectmanager", userService.getUser(p.getProject_leader_id()));
            model.addAttribute("customer", customerService.getCustomer(p.getCustomer_id()));
            model.addAttribute("tasks", tasks );
            model.addAttribute("project", p);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("workingHours", taskService.getWorkinghours(taskService.getSubTasks(id)));
            return "project/viewProject";
        }
    }

    // Responds to /deleteProject?id=project_id
    @RequestMapping(value = "/deleteProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteProject(@RequestParam int id, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            projectService.deleteProject(id);
            return "redirect:/listProject";
        }
    }

    @PostMapping("/addParticipant")
    public String addParticipant(@RequestParam int user_id, int project_id, int project_role_id, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            projectService.assignParticipant(user_id, project_id, project_role_id);
            return "redirect:/viewProject?id=" + project_id;
        }
    }

    @PostMapping("/removeParticipant")
    public String removeParticipant(WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String uId = request.getParameter("user_id");
            String pId = request.getParameter("project_id");
            int user_id = Integer.parseInt(uId);
            int project_id = Integer.parseInt(pId);
            projectService.removeParticipant(user_id, project_id);
        return "redirect:/viewProject?id=" + project_id;

        }
    }
}