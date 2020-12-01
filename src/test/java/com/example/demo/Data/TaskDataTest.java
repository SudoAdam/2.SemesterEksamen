/**
 * This test is specifically made ordered.
 * Ordered tests are needed when testing data requests due to CRUD (Create, Read, Edit, Delete)
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Task;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskDataTest {
    private final TaskData taskData = new TaskData();

    @Test
    @Order(0)
    void createTask() {
        int project_id = 1;
        String task_name = "Make clean!";
        String task_description = "The software has a lot of mess in it...";
        int task_leader_id = 1;
        LocalDate kickoff = LocalDate.of(2020,12,2);
        LocalDate deadline = LocalDate.of(2020,12,8);
        int working_hours = 37;

        boolean result = taskData.createTask(project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);

        // Test return value that should be true
        assertTrue(result);
    }

    @Test
    @Order(1)
    void getTasks() {
        ArrayList<Task> list = taskData.getTasks(1);
        Task t = list.get(list.size()-1);

        assertEquals(1,t.getProject_id());
        assertEquals("Make clean!",t.getTask_name());
        assertEquals("The software has a lot of mess in it...",t.getTask_description());
        assertEquals(1,t.getTask_leader_id());
        assertEquals(LocalDate.of(2020,12,2),t.getKickoff());
        assertEquals(LocalDate.of(2020,12,8),t.getDeadline());
        assertEquals(37,t.getWorking_hours());
    }

    @Test
    @Order(2)
    void editTask() {
        ArrayList<Task> list = taskData.getTasks(1);

        int task_id = list.get(list.size()-1).getTask_id();
        int project_id = 1;
        String task_name = "Make clean!";
        String task_description = "The software has a lot of mess in it... There's even more now!";
        int task_leader_id = 2;
        LocalDate kickoff = LocalDate.of(2020,12,2);
        LocalDate deadline = LocalDate.of(2020,12,9);
        int working_hours = 48;

        boolean result = taskData.editTask(task_id, project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours);

        // Test return result that should be true
        assertTrue(result);

        ArrayList<Task> list_new = taskData.getTasks(1);
        Task t = list.get(list_new.size()-1);

        System.out.println(t.getTask_id());

        // Test that data transfer is correctly translated
        assertEquals(t.getTask_id(),list_new.get(list.size()-1).getTask_id());
        assertEquals(1,t.getProject_id());
        assertEquals(task_name,t.getTask_name());
        assertEquals(task_description,t.getTask_description());
        assertEquals(2,t.getTask_leader_id());
        assertEquals(LocalDate.of(2020,12,2),t.getKickoff());
        assertEquals(LocalDate.of(2020,12,9),t.getDeadline());
        assertEquals(48,t.getWorking_hours());
    }
}