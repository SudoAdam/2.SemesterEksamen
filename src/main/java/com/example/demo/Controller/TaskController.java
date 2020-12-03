package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@Controller
public class TaskController {
    TaskService taskService = new TaskService();
    UserService userService = new UserService();
    ProjectService projectService = new ProjectService();

    // Responds to /editProject?id=project_id
    @RequestMapping(value = "/editTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String editTask(@RequestParam int id, Model model) {
        model.addAttribute("project", projectService.getProject(id));
        return "project/editTask";
    }

@RequestMapping( value = "/createTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTask(@RequestParam int project_id, Model model){
    model.addAttribute("project", projectService.getProject(project_id));
    return "project/createTask";
}
@PostMapping("/createTaskPost")
    public String createTaskPost(WebRequest request, Model model) {
    String projectId = request.getParameter("projectId");
    String taskName = request.getParameter("taskName");
    String taskDesc = request.getParameter("taskDesc");
    String taskLeaderEmail = request.getParameter("taskLeaderEmail");
    String taskKickoff = request.getParameter("kickoff");
    String taskDeadline = request.getParameter("deadline");
    String workingHoursSTR = request.getParameter("workingHours");

    LocalDate kickoff = LocalDate.parse(taskKickoff);
    LocalDate deadline = LocalDate.parse(taskDeadline);
    int workingHours = Integer.parseInt(workingHoursSTR);
    int project_leader_id = userService.findUserIdFromEmail(taskLeaderEmail);
    int pId = Integer.parseInt(projectId);

    taskService.createTask(pId, taskName, taskDesc, project_leader_id, kickoff, deadline, workingHours);
    model.addAttribute("project", projectService.getProject(pId));
    return "project/viewProject";
}

    /*@PostMapping("/updateTask")
    public String updateTask(WebRequest request, Model model) {
        String taskId = request.getParameter("tId");
        String taskName = request.getParameter("tName");
        String taskDesc = request.getParameter("tDesc");
        String taskWH = request.getParameter("tWH");
        String kickOffStr = request.getParameter("tKickoff");
        String deadlineStr = request.getParameter("tDeadline");
        String taskLeader = request.getParameter("tLeader");

        int tId = Integer.parseInt(taskId);
        LocalDate kickoff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);

        taskService.editTask(taskId,)
        model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));

        return "project/viewProject";
    }*/

}
