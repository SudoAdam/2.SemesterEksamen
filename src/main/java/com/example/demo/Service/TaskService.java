package com.example.demo.Service;

import com.example.demo.Data.SubTaskData;
import com.example.demo.Data.TaskData;
import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskData taskData;
    private final SubTaskData subTaskData;
    private final UserService userService;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskService(UserService userService, TaskData taskData, SubTaskData subTaskData) {
        this.userService = userService;
        this.taskData = taskData;
        this.subTaskData =subTaskData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Task task) throws QueryDeniedException, FailedRequestException {
        int task_leader_id = task.getTask_leader_id();
        User task_leader = userService.getUser(task_leader_id);
        task.setTask_leader(task_leader);
        String e_mail = userService.findEmailFromUserId(task_leader_id);
        task.setTask_leader_email(e_mail);
    }

    private boolean correctDate(LocalDate kickoff, LocalDate deadline) {
        boolean result = false;
        if (kickoff.isBefore(deadline)) {
            result = true;
        }
        return result;
    }

    public ArrayList<Task> getTasks(int project_id) throws FailedRequestException {
        try {
        ArrayList<Task> list = taskData.getTasks(project_id);
        return list;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public Task getTask(int task_id) throws FailedRequestException {
        try {
        Task t = taskData.getTask(task_id);
        finalize(t);
        return (t);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws DateContextException, FailedRequestException {
        try {
        if (!correctDate(kickoff, deadline)) {
            throw new DateContextException();
        }
        taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws FailedRequestException {
        try {
        taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteTask(int id) throws FailedRequestException {
        try {
        taskData.deleteTask(id);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteSubTask(int task_id, int sub_task_id) throws FailedRequestException {
        try {
        subTaskData.deleteSubTask(task_id, sub_task_id);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createSubTask(int task_id, String name, String description) throws FailedRequestException {
        try {
        subTaskData.createSubTask(task_id,description,name);
        } catch (ExecuteDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public ArrayList<SubTask> getSubTasks(int project_id) throws FailedRequestException {
        try {
        return subTaskData.getSubTasks(project_id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public SubTask getSubTask(int task_id, int sub_task_id) throws FailedRequestException {
        try{
        return subTaskData.getSubTask(task_id, sub_task_id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
