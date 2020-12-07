package com.example.demo.Service;

import com.example.demo.Data.TaskData;
import com.example.demo.Domain.Project;
import com.example.demo.Domain.Task;
import com.example.demo.Domain.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskData taskData;
    private final UserService userService;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskService(UserService userService, TaskData taskData){
        this.userService = userService;
        this.taskData = taskData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Task task) throws SQLException {
        int task_leader_id = task.getTask_leader_id();
        User task_leader = userService.getUser(task_leader_id);
        task.setTask_leader(task_leader);
        String e_mail = userService.findEmailFromUserId(task_leader_id);
        task.setTask_leader_email(e_mail);
    }

    private void finalize(ArrayList<Task> list) throws SQLException{
        // Late dependency injection for collections of domain objects
        for (Task t: list) {
            finalize(t);
        }
    }

    public ArrayList<Task> getTasks(int project_id) throws SQLException{
        ArrayList<Task> list = taskData.getTasks(project_id);
        finalize(list);
        return list;
    }

    public Task getTask(int task_id) throws SQLException{
        Task t = taskData.getTask(task_id);
        finalize(t);
        return (t);
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws SQLException{
        taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws SQLException{
        taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }
}
