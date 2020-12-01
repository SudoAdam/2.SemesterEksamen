package com.example.demo.Controller;

import com.example.demo.Domain.Project;
import com.example.demo.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    ProjectService projectService = new ProjectService();

    @GetMapping("/listProject")
    public String showProjects(Model model) {
    ArrayList<Project> projectList = projectService.getProjects();
    model.addAttribute("projectList", projectList);
    return "project/listProject";
    }

    @GetMapping("/createProject")
    public String createProject() {
        return "project/createProject";
    }

    @PostMapping("/createProject")
    public String createProject(WebRequest request) {
        //denne funktion er ikke færdig!
        String projectName = request.getParameter("pName");
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String kickOffStr = request.getParameter("kickOff");
        String deadlineStr = request.getParameter("deadline");
        int project_leader_id = 1;
        int customer_id = 1;
        LocalDate kickOff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);

        projectService.createProject(projectName, kickOff, deadline, project_leader_id, customer_id);

        return "errors/defaultError";
    }

    // Responds to /viewProject?id=project_id
    @RequestMapping(value = "/viewProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewProject(@RequestParam int id, Model model) {
        model.addAttribute("project", projectService.getProject(id));
        return "project/viewProject";
    }

}
