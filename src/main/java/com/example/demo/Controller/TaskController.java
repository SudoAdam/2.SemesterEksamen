package com.example.demo.Controller;

import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class TaskController {
    TaskService taskService = new TaskService();
    UserService userService = new UserService();

@GetMapping("/createTask")
    public String createTask(){

    return "project/createTask";
}
@PostMapping("/createTask")
    public String createTask(WebRequest request) {
    String taskId = request.getParameter("taskId");
    String taskName = request.getParameter("taskName");
    String taskDesc = request.getParameter("taskDesc");
    String taskLeaderEmail = request.getParameter("taskLeaderEmail");
    String taskKickoff = request.getParameter("kickoff");
    String taskDeadline = request.getParameter("deadline");
    String workingHoursSTR = request.getParameter("workingHours");

    int id = Integer.parseInt(taskId);
    LocalDate kickoff = LocalDate.parse(taskKickoff);
    LocalDate deadline = LocalDate.parse(taskDeadline);
    int workingHours = Integer.parseInt(workingHoursSTR);
    int project_leader_id = userService.findUserIdFromEmail(taskLeaderEmail);

    taskService.createTask(id, taskName, taskDesc, project_leader_id, kickoff, deadline, workingHours);


    return "project/showTask";
}

}
