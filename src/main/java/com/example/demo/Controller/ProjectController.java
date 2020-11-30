package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class ProjectController {

    @GetMapping("/projectList")
    public String showProjects() {
        return "lists/projectList";
    }

    @GetMapping("/projectCreate")
    public String createProject() {
        return "create/projectCreate";
    }


    @PostMapping("/projectCreate")
    public String createingProject(WebRequest request) {
        //denne funktion er ikke færdig!
        String projectName = request.getParameter("pName");
        String companyName = request.getParameter("comName");
        String contactName = request.getParameter("conName");
        String contactEmail = request.getParameter("conEmail");
        String kickOffStr = request.getParameter("kickOff");
        String deadlineStr = request.getParameter("deadline");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate kickOff = LocalDate.parse(kickOffStr,dateTimeFormatter);
        LocalDate deadline = LocalDate.parse(deadlineStr,dateTimeFormatter); ;
        ProjectService projectService = new ProjectService();
        //projectService.createProject(projectName,kickOff, deadline,);

        return "error";
    }

}
