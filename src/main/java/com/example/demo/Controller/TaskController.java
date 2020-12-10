package com.example.demo.Controller;

import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
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
    public String editTask(@RequestParam int id, Model model) throws QueryDeniedException {
        int project_id = taskService.getTask(id).getProject_id();
        model.addAttribute("participants", projectService.getParticipants(project_id));
        model.addAttribute("project", projectService.getProject(project_id));
        model.addAttribute("task", taskService.getTask(id));
        return "project/editTask";
    }

    @PostMapping("/updateTask")
    public String updateTask(WebRequest request, Model model) throws QueryDeniedException, ExecuteDeniedException {
        // Consider refactoring to handle mapping in either Data or Mapping layer
        String taskId = request.getParameter("tId");
        String projectId = request.getParameter("pId");
        String taskName = request.getParameter("tName");
        String taskDesc = request.getParameter("tDesc");
        String taskLeader = request.getParameter("taskLeader");
        String kickOffStr = request.getParameter("kickoff");
        String deadlineStr = request.getParameter("deadline");
        String workingHours = request.getParameter("wh");

        int tId = Integer.parseInt(taskId);
        int pId = Integer.parseInt(projectId);
        int project_leader_id = userService.findUserIdFromEmail(taskLeader);
        LocalDate kickoff = LocalDate.parse(kickOffStr);
        LocalDate deadline = LocalDate.parse(deadlineStr);
        int wh = Integer.parseInt(workingHours);

        taskService.editTask(tId, pId, taskName, taskDesc, project_leader_id, kickoff, deadline, wh);
        model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));
        model.addAttribute("tasks", taskService.getTasks(pId));
        return "redirect:/viewProject?id=" + pId;
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTask(@RequestParam int id, Model model) throws QueryDeniedException {
        model.addAttribute("project", projectService.getProject(id));
        model.addAttribute("participants", projectService.getParticipants(id));
        return "project/createTask";
    }

    @RequestMapping(value = "/createTaskPost", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTaskPost(@RequestParam int id, WebRequest request, Model model) throws Exception {

        String taskName = request.getParameter("taskName");
        String taskDesc = request.getParameter("taskDesc");
        String taskLeader = request.getParameter("taskLeader");
        String taskKickoff = request.getParameter("kickoff");
        String taskDeadline = request.getParameter("deadline");
        String workingHoursSTR = request.getParameter("workingHours");

        LocalDate kickoff = LocalDate.parse(taskKickoff);
        LocalDate deadline = LocalDate.parse(taskDeadline);
        int workingHours = Integer.parseInt(workingHoursSTR);
        int project_leader_id = userService.findUserIdFromEmail(taskLeader);
        taskService.createTask(id, taskName, taskDesc, project_leader_id, kickoff, deadline, workingHours);


        model.addAttribute("project", projectService.getProject(id));
        return "redirect:/viewProject?id=" + id;
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

    @RequestMapping(value = "/deleteTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteTask(@RequestParam int id) throws QueryDeniedException, ExecuteDeniedException {
        Task task = taskService.getTask(id);
        int project_id = task.getProject_id();
        taskService.deleteTask(id);
        return "redirect:/viewProject?id=" + project_id;
    }

    @RequestMapping(value = "/deleteSubTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteSubTask(@RequestParam int id) throws QueryDeniedException, ExecuteDeniedException {

        SubTask subTask = TaskService.getSubTask(id);
        int project_id = task.getProject_id();
        taskService.deleteTask(id);
        return "redirect:/viewProject?id=" + project_id;
    }

    @RequestMapping(value = "/createSubTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createSubTask(WebRequest request) throws ExecuteDeniedException {
        String stName = request.getParameter("sub_task_name");
        String stDesc = request.getParameter("sub_task_description");
        String tId = request.getParameter("task_id");
        String pId = request.getParameter("project_id");
        int task_id = Integer.parseInt(tId);
        int project_id = Integer.parseInt(pId);
        taskService.createSubTask(task_id, stName, stDesc);

        return "redirect:/viewProject?id=" + project_id;
    }

}
