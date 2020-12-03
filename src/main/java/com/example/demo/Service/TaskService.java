package com.example.demo.Service;

import com.example.demo.Data.TaskData;
import com.example.demo.Domain.Project;
import com.example.demo.Domain.Task;
import com.example.demo.Domain.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskData taskData;
    private final UserService userService;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskService(){
        this.userService = new UserService();
        this.taskData = new TaskData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Task task) {
        int task_leader_id = task.getTask_leader_id();
        User task_leader = userService.getUser(task_leader_id);
        task.setTask_leader(task_leader);
    }

    private void finalize(ArrayList<Task> list) {
        // Late dependency injection for collections of domain objects
        for (Task t: list) {
            finalize(t);
        }
    }

    public ArrayList<Task> getTasks(int project_id) {
        ArrayList<Task> list = taskData.getTasks(project_id);
        finalize(list);
        return list;
    }

    public boolean createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours){
        return taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }

    public boolean editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) {
        return taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }
}
