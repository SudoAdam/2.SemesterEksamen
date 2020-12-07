package com.example.demo.Controller;

import com.example.demo.Service.ProjectService;
import com.example.demo.Service.TaskService;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.time.LocalDate;

@Controller
public class TaskController {
    TaskService taskService;
    UserService userService;
    ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    // Responds to /editTask?id=task_id
    @RequestMapping(value = "/editTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String editTask(@RequestParam int id, Model model) throws SQLException {
        int project_id = taskService.getTask(id).getProject_id();
        model.addAttribute("project", projectService.getProject(project_id));
        model.addAttribute("task", taskService.getTask(id));
        return "project/editTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(WebRequest request, Model model) throws SQLException {
        String taskId = request.getParameter("tId");
        String projectId = request.getParameter("pId");
        String taskName = request.getParameter("tName");
        String taskDesc = request.getParameter("tDesc");
        String taskLeaderEmail = request.getParameter("taskLeaderEmail");
        String kickOffStr = request.getParameter("kickoff");
        String deadlineStr = request.getParameter("deadline");
        String workingHours = request.getParameter("wh");

        int tId = Integer.parseInt(taskId);
        int pId = Integer.parseInt(projectId);
        int project_leader_id = userService.findUserIdFromEmail(taskLeaderEmail);
        LocalDate kickoff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);
        int wh = Integer.parseInt(workingHours);

        taskService.editTask(tId, pId, taskName, taskDesc, project_leader_id, kickoff, deadline, wh);
        model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));
        model.addAttribute("tasks", taskService.getTasks(pId));
        return "project/viewProject";
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTask(@RequestParam int id, Model model) throws SQLException {
        model.addAttribute("project", projectService.getProject(id));
        return "project/createTask";
    }

    @RequestMapping(value = "/createTaskPost", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTaskPost(@RequestParam int id, WebRequest request, Model model) throws SQLException {

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
