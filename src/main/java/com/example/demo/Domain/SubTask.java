package com.example.demo.Domain;

/**
 * @author Adam Madsen
 * @version 1.0
 * @since 09-12-2020
 */

public class SubTask implements DomainInterface {

    private int task_id;
    private String sub_task_description;
    private int sub_task_id;
    private String sub_task_name;
    int sub_task_hours;

    public SubTask(int sub_task_id, String sub_task_name, String sub_task_description, int task_id, int sub_task_hours) {
        this.task_id = task_id;
        this.sub_task_description = sub_task_description;
        this.sub_task_id = sub_task_id;
        this.sub_task_name = sub_task_name;
        this.sub_task_hours = sub_task_hours;
    }

    public int getTask_id() {
        return task_id;
    }

    public String getSub_task_description() {
        return sub_task_description;
    }

    public int getSub_task_id() {
        return sub_task_id;
    }

    public String getSub_task_name() {
        return sub_task_name;
    }

    public int getSub_task_hours() {
        return sub_task_hours;
    }

    public void setSub_task_hours(int sub_task_hours) {
        this.sub_task_hours = sub_task_hours;
    }
}
