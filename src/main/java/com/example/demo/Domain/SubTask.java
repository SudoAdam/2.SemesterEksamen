package com.example.demo.Domain;
/**
 * @author Adam Madsen
 * @version 1.0
 * @since 09-12-2020
 */

public class SubTask implements DomainFacade {

    private int task_id;
    private String sub_task_discription;
    private int sub_task_id;
    private String sub_task_name;

    public SubTask(int sub_task_id, String sub_task_name, String sub_task_discription, int task_id) {
        this.task_id = task_id;
        this.sub_task_discription = sub_task_discription;
        this.sub_task_id = sub_task_id;
        this.sub_task_name = sub_task_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getSub_task_discription() {
        return sub_task_discription;
    }

    public void setSub_task_discription(String sub_task_discription) {
        this.sub_task_discription = sub_task_discription;
    }

    public int getSub_task_id() {
        return sub_task_id;
    }

    public void setSub_task_id(int sub_task_id) {
        this.sub_task_id = sub_task_id;
    }

    public String getSub_task_name() {
        return sub_task_name;
    }

    public void setSub_task_name(String sub_task_name) {
        this.sub_task_name = sub_task_name;
    }
}
