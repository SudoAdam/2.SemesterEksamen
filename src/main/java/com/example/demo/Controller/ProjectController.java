package com.example.demo.Controller;

import com.example.demo.Domain.Project;
import com.example.demo.Domain.User;
import com.example.demo.Service.ProjectService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    ProjectService projectService = new ProjectService();
    TaskService taskService = new TaskService();
    UserService userService = new UserService();

    @GetMapping("/listProject")
    public String showProjects(Model model, WebRequest request) throws SQLException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        model.addAttribute("projectList", projectService.getProjects());

        /* Kode til kun at vise de projekter der er tilknyttet ens bruger
            Hvis bruger er admin vises alle projekter

        ArrayList<Project> allProjectList = projectService.getProjects();
        ArrayList<Project> userProjectList = new ArrayList<>();

        if (user.getIs_admin() == 0){
            for(Project p: allProjectList){
                if(p.getEmployee_id == user.getUser_id()){
                    userProjectList.add(p);
                }
            }
            model.addAttribute("projectList", userProjectList);
        } else {
            model.addAttribute("projectList", allProjectList);
        }

        model.addAttribute("projectList", projectList);
        */
        return "project/listProject";
    }



    @GetMapping("/createProject")
    public String createProject() {
        return "project/createProject";
    }

    @PostMapping("/createProject")
    public String createProject(WebRequest request, Model model) throws SQLException {
        //denne funktion er ikke færdig!
        String projectName = request.getParameter("pName");
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String kickOffStr = request.getParameter("kickOff");
        String deadlineStr = request.getParameter("deadline");
        int project_leader_id = 1;                                  //request email and get project_leader_id
        int customer_id = 1;
        LocalDate kickOff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);

        projectService.createProject(projectName, kickOff, deadline, project_leader_id, customer_id);
        ArrayList<Project> projectList = projectService.getProjects();
        model.addAttribute("projectList", projectList);
        return "project/listProject";
    }

    // Responds to /editProject?id=project_id
    @RequestMapping(value = "/editProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String editProject(@RequestParam int id, Model model) throws SQLException {
        model.addAttribute("project", projectService.getProject(id));
        return "project/editProject";
    }

    @PostMapping("/updateProject")
    public String updateProject(WebRequest request, Model model) throws SQLException {
        String projectId = request.getParameter("id");
        String projectName = request.getParameter("pName");
        String kickOffStr = request.getParameter("kickOff");
        String deadlineStr = request.getParameter("deadline");
        String pLeaderId = request.getParameter("plId");
        String CustomerId = request.getParameter("cid");

        int pId = Integer.parseInt(projectId);
        LocalDate kickoff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);
        int pLId = Integer.parseInt(pLeaderId);
        int cId = Integer.parseInt(CustomerId);

        projectService.editProject(pId, projectName, kickoff, deadline, pLId, cId);
        model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));

        return "project/viewProject";
    }

    // Responds to /viewProject?id=project_id
    @RequestMapping(value = "/viewProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewProject(@RequestParam int id, WebRequest request, Model model) throws SQLException {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);


        model.addAttribute("tasks", taskService.getTasks(id));
        model.addAttribute("project", projectService.getProject(id));
        return "project/viewProject";
    }
}
