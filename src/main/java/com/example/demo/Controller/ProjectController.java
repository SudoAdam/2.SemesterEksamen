package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

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

        return "error";
    }

}
