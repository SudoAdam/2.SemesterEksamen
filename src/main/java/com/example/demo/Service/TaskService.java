/*
* @Author Rasmus and Kasper
* */
package com.example.demo.Service;

import com.example.demo.Data.SubTaskData;
import com.example.demo.Data.TaskData;
import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import com.example.demo.Exceptions.ServiceExceptions.DateContextException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskData taskData;
    private final SubTaskData subTaskData;
    private final UserService userService;
    private final DateLogic dateLogic;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskService(UserService userService, TaskData taskData, SubTaskData subTaskData, DateLogic dateLogic) {
        this.userService = userService;
        this.taskData = taskData;
        this.subTaskData = subTaskData;
        this.dateLogic = dateLogic;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   /* private void finalize(Task task) throws QueryDeniedException, FailedRequestException {
        int task_leader_id = task.getTask_leader_id();
        User task_leader = userService.getUser(task_leader_id);
        task.setTask_leader(task_leader);
        String e_mail = userService.findEmailFromUserId(task_leader_id);
        task.setTask_leader_email(e_mail);
    } */

    public ArrayList<Task> getTasks(int project_id) throws FailedRequestException {
        try {
            ArrayList<Task> list = taskData.getTasks(project_id);
            return list;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public int getWorkinghours(ArrayList<SubTask> list) {
        int totalTime = 0;
        for (int i = 0; i < list.size(); i++) {
            int time = list.get(i).getSub_task_hours();
            totalTime += time;
        }
        return totalTime;
    }

    public int getTaskhours(ArrayList<SubTask> list) {
        int totalTime = 0;
        for (int i = 0; i < list.size(); i++) {
            int time = list.get(i).getSub_task_hours();
            totalTime += time;
        }
        return totalTime;
    }

    public ArrayList<Task> getTaskHours(ArrayList<Task> tasks, ArrayList<SubTask> subTasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int id = tasks.get(i).getTask_id();
            int time = 0;
            for (int j = 0; j < subTasks.size(); j++) {
                if (subTasks.get(j).getTask_id() == id) {
                    time += subTasks.get(j).getSub_task_hours();
                }
            }
            tasks.get(i).setTask_hours(time);
        }
        return tasks;
    }

    public Task getTask(int task_id) throws FailedRequestException {
        try {
            Task t = taskData.getTask(task_id);
            return (t);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline) throws DateContextException, FailedRequestException {
        try {
            if (!dateLogic.correctDate(kickoff, deadline)) {
                throw new DateContextException();
            }
            taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline) throws FailedRequestException {
        try {
            taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteTask(int id) throws FailedRequestException {
        try {
            taskData.deleteTask(id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteSubTask(int task_id, int sub_task_id) throws FailedRequestException {
        try {
            subTaskData.deleteSubTask(task_id, sub_task_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createSubTask(int task_id, String name, String description, int hours) throws FailedRequestException {
        try {
            subTaskData.createSubTask(task_id, description, name, hours);
        } catch (OperationDeniedException e) {
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
        try {
            return subTaskData.getSubTask(task_id, sub_task_id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
