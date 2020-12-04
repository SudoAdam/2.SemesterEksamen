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

    // Responds to /editTask?id=task_id
    @RequestMapping(value = "/editTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String editTask(@RequestParam int id, Model model) {
        model.addAttribute("task", taskService.getTask(id));
        return "project/editTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(WebRequest request, Model model) {
        String taskId = request.getParameter("tId");
        String projectId = request.getParameter("pId");
        String taskName = request.getParameter("tName");
        String taskDesc = request.getParameter("tDesc");
        String taskLeader = request.getParameter("tLId");
        String kickOffStr = request.getParameter("kickoff");
        String deadlineStr = request.getParameter("deadline");
        String workingHours = request.getParameter("wh");

        int tId = Integer.parseInt(taskId);
        int pId = Integer.parseInt(projectId);
        int tLId = Integer.parseInt(taskLeader);
        LocalDate kickoff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);
        int wh = Integer.parseInt(workingHours);

        taskService.editTask(tId, pId, taskName, taskDesc, tLId, kickoff, deadline, wh);
        model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));
        model.addAttribute("tasks", taskService.getTasks(pId));
        return "project/viewProject";
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTask(@RequestParam int id, Model model) {
        model.addAttribute("project", projectService.getProject(id));
        return "project/createTask";
    }

    @RequestMapping(value = "/createTaskPost", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTaskPost(@RequestParam int id, WebRequest request, Model model) {

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


        taskService.createTask(id, taskName, taskDesc, project_leader_id, kickoff, deadline, workingHours);
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("tasks", taskService.getTasks(id));
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
