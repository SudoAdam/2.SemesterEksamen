package com.example.demo.Service;

import com.example.demo.Data.SubTaskData;
import com.example.demo.Data.TaskData;
import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;

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
    private void finalize(Task task) throws QueryDeniedException {
        int task_leader_id = task.getTask_leader_id();
        User task_leader = userService.getUser(task_leader_id);
        task.setTask_leader(task_leader);
        String e_mail = userService.findEmailFromUserId(task_leader_id);
        task.setTask_leader_email(e_mail);
    }

    private void finalize(ArrayList<Task> list) throws QueryDeniedException {
        // Late dependency injection for collections of domain objects
        for (Task t : list) {
            finalize(t);
        }
    }

    public ArrayList<Task> getTasks(int project_id) throws QueryDeniedException {
        ArrayList<Task> list = taskData.getTasks(project_id);
        // finalize(list);
        return list;
    }

    public int getWorkinghours(ArrayList<Task> list){
       int totalTime = 0;
        for (int i = 0; i < list.size(); i++) {
           int time = list.get(i).getWorking_hours();
            totalTime += time;
        }
        return totalTime;
    }

    public Task getTask(int task_id) throws QueryDeniedException {
        Task t = taskData.getTask(task_id);
        finalize(t);
        return (t);
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws Exception {
        if (correctDate(kickoff, deadline) == false) {
            throw new Exception();
        }
        taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }

    private boolean correctDate(LocalDate kickoff, LocalDate deadline) {
        boolean result = false;
        if (kickoff.isBefore(deadline)) {
            result = true;
        }
        return result;
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws ExecuteDeniedException {
        taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);
    }

    public void deleteTask(int id) throws ExecuteDeniedException {
        taskData.deleteTask(id);
    }

    public void deleteSubTask(int id) throws ExecuteDeniedException {
        subTaskData.deleteSubTask(id);
    }

    public void createSubTask(int task_id, String name, String description) throws ExecuteDeniedException {
        subTaskData.createSubTask(task_id,description,name);
    }

    public ArrayList<SubTask> getSubTasks(int project_id) throws QueryDeniedException {
        return subTaskData.getSubTasks(project_id);
    }

    public SubTask getSubTask(int sub_task_id) throws QueryDeniedException {
        return subTaskData.getSubTask(sub_task_id);
    }
}
