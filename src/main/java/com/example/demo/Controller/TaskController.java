package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class TaskController {
    TaskService taskService = new TaskService();
    UserService userService = new UserService();
    ProjectService projectService = new ProjectService();

@GetMapping("/createTask")
    public String createTask(){
    return "project/createTask";
}
@PostMapping("/createTask")
    public String createTask(WebRequest request, Model model) {
    String taskId = request.getParameter("taskId");
    String taskName = request.getParameter("taskName");
    String taskDesc = request.getParameter("taskDesc");
    String taskLeaderEmail = request.getParameter("taskLeaderEmail");
    String taskKickoff = request.getParameter("kickoff");
    String taskDeadline = request.getParameter("deadline");
    String workingHoursSTR = request.getParameter("workingHours");
    String projectId = request.getParameter("projectId");

    int idTask = Integer.parseInt(taskId);
    LocalDate kickoff = LocalDate.parse(taskKickoff);
    LocalDate deadline = LocalDate.parse(taskDeadline);
    int workingHours = Integer.parseInt(workingHoursSTR);
    int project_leader_id = userService.findUserIdFromEmail(taskLeaderEmail);
    int pId = Integer.parseInt(projectId);

    taskService.createTask(idTask, taskName, taskDesc, project_leader_id, kickoff, deadline, workingHours);
    model.addAttribute("project", projectService.getProject(pId));
    return "project/viewProject";
}

}
