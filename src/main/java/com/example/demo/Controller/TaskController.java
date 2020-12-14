package com.example.demo.Controller;

import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.ServiceExceptions.DateContextException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;
import com.example.demo.Exceptions.ServiceExceptions.LoginException;
import com.example.demo.Service.LoginLogic;
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
    LoginLogic loginLogic;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService, LoginLogic loginLogic) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
        this.loginLogic = loginLogic;
    }

    // Responds to /editTask?id=task_id
    @RequestMapping(value = "/editTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String editTask(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            int project_id = taskService.getTask(id).getProject_id();
            model.addAttribute("participants", projectService.getParticipants(project_id));
            model.addAttribute("project", projectService.getProject(project_id));
            model.addAttribute("task", taskService.getTask(id));
            return "project/editTask";
        }
    }

    @PostMapping("/updateTask")
    public String updateTask(WebRequest request, Model model) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            // Consider refactoring to handle mapping in either Data or Mapping layer
            String taskId = request.getParameter("tId");
            String projectId = request.getParameter("pId");
            String taskName = request.getParameter("tName");
            String taskDesc = request.getParameter("tDesc");
            String taskLeader = request.getParameter("taskLeader");
            String kickOffStr = request.getParameter("kickoff");
            String deadlineStr = request.getParameter("deadline");

            int tId = Integer.parseInt(taskId);
            int pId = Integer.parseInt(projectId);
            int project_leader_id = userService.findUserIdFromEmail(taskLeader);
            LocalDate kickoff = LocalDate.parse(kickOffStr);
            LocalDate deadline = LocalDate.parse(deadlineStr);

            taskService.editTask(tId, pId, taskName, taskDesc, project_leader_id, kickoff, deadline);
            model.addAttribute("project", projectService.getProject(Integer.parseInt(projectId)));
            model.addAttribute("tasks", taskService.getTasks(pId));
            return "redirect:/viewProject?id=" + pId;
        }
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTask(@RequestParam int id, Model model, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            model.addAttribute("project", projectService.getProject(id));
            model.addAttribute("participants", projectService.getParticipants(id));
            return "project/createTask";
        }
    }

    @RequestMapping(value = "/createTaskPost", method = {RequestMethod.GET, RequestMethod.POST})
    public String createTaskPost(@RequestParam int id, WebRequest request) throws DateContextException, FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String taskName = request.getParameter("taskName");
            String taskDesc = request.getParameter("taskDesc");
            String taskLeader = request.getParameter("taskLeader");
            String taskKickoff = request.getParameter("kickoff");
            String taskDeadline = request.getParameter("deadline");

            LocalDate kickoff = LocalDate.parse(taskKickoff);
            LocalDate deadline = LocalDate.parse(taskDeadline);
            int project_leader_id = Integer.parseInt(taskLeader);
            taskService.createTask(id, taskName, taskDesc, project_leader_id, kickoff, deadline);
            return "redirect:/viewProject?id=" + id;
        }
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
    public String deleteTask(@RequestParam int id, WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            Task task = taskService.getTask(id);
            int project_id = task.getProject_id();
            taskService.deleteTask(id);
            return "redirect:/viewProject?id=" + project_id;
        }
    }

    @PostMapping("/deleteSubTask")
    public String deleteSubTask(WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String pId = request.getParameter("project_id");
            String tId = request.getParameter("task_id");
            String stId = request.getParameter("sub_task_id");
            int project_id = Integer.parseInt(pId);
            int task_id = Integer.parseInt(tId);
            int sub_task_id = Integer.parseInt(stId);
            taskService.deleteSubTask(task_id, sub_task_id);
            return "redirect:/viewProject?id=" + project_id;
        }
    }

    @RequestMapping(value = "/createSubTask", method = {RequestMethod.GET, RequestMethod.POST})
    public String createSubTask(WebRequest request) throws FailedRequestException, LoginException {
        if (!loginLogic.checkLogin(request)) {
            throw new LoginException();
        } else {
            String stName = request.getParameter("sub_task_name");
            String stDesc = request.getParameter("sub_task_description");
            String tId = request.getParameter("task_id");
            String pId = request.getParameter("project_id");
            String sHour= request.getParameter("sub_task_hours");
            int task_id = Integer.parseInt(tId);
            int project_id = Integer.parseInt(pId);
            int hours = Integer.parseInt(sHour);
            taskService.createSubTask(task_id, stName, stDesc,hours);

            return "redirect:/viewProject?id=" + project_id;
        }
    }
}
