package com.example.demo.Domain;

import java.time.LocalDate;

public class Task implements DomainInterface {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int task_id;
    private int project_id;
    private String task_name;
    private String task_description;
    private int task_leader_id;
    private User task_leader;
    private String task_leader_email;
    private LocalDate kickoff;
    private LocalDate deadline;
    private int task_hours;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Task(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline) {
        this.task_id = task_id;
        this.project_id = project_id;
        this.task_name = task_name;
        this.task_description = task_description;
        this.task_leader_id = task_leader_id;
        this.kickoff = kickoff;
        this.deadline = deadline;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public int getTask_leader_id() {
        return task_leader_id;
    }

    public void setTask_leader_id(int task_leader_id) {
        this.task_leader_id = task_leader_id;
    }

    public User getTask_leader() {
        return task_leader;
    }

    public void setTask_leader(User task_leader) {
        this.task_leader = task_leader;
    }

    public String getTask_leader_email() {
        return task_leader_email;
    }

    public void setTask_leader_email(String task_leader_email) {
        this.task_leader_email = task_leader_email;
    }

    public LocalDate getKickoff() {
        return kickoff;
    }

    public void setKickoff(LocalDate kickoff) {
        this.kickoff = kickoff;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getTask_hours() {
        return task_hours;
    }

    public void setTask_hours(int task_hours) {
        this.task_hours = task_hours;
    }
}
