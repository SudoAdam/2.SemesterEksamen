package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class TaskController {


@GetMapping("/createTask")
    public String createTask(){

    return "project/createTask";
}
@PostMapping("/createTask")
    public String createTask(WebRequest request){
    String taskName = request.getParameter("taskName");
    String taskDisp = request.getParameter("taskDisp");
    String taskLeaderEmail = request.getParameter("taskLeaderEmail");
    String taskKickoff = request.getParameter("kickoff");
    String taskDeadline = request.getParameter("deadline");
    String workingHoursSTR = request.getParameter("workingHours");

    LocalDate kickoff = LocalDate.parse(taskKickoff);
    LocalDate deadline = LocalDate.parse(taskDeadline);
    int workingHours = Integer.parseInt(workingHoursSTR);

    return "project/showTask";
}

}
