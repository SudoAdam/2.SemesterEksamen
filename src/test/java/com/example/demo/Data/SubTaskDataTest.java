package com.example.demo.Data;

import com.example.demo.Domain.SubTask;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskDataTest {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final TaskData taskData;
    private final SubTaskData subTaskData;
    private int task_id;
    private int sub_task_id;
    private final String sub_task_description;
    private final String sub_task_name;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SubTaskDataTest(ApplicationContext ctx) {
        this.projectData = (ProjectData) ctx.getBean("projectData");
        this.taskData = (TaskData) ctx.getBean("taskData");
        this.subTaskData = (SubTaskData) ctx.getBean("subTaskData");
        this.sub_task_description = "testing of sub task";
        this.sub_task_name = "SubTask test";
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void assertSubTask(SubTask subTask) {
        assertEquals(task_id, subTask.getTask_id());
        assertEquals(sub_task_id, subTask.getSub_task_id());
        assertEquals(sub_task_description, subTask.getSub_task_description());
        assertEquals(sub_task_name, subTask.getSub_task_name());
    }

    @BeforeEach
    void init() throws QueryDeniedException, ExecuteDeniedException {
        // Every test requires a user object, we will make it for every test.
        /*
        projectData.deleteProject();
        projectData.createProject();

        taskData.deleteTask();
        taskData.createTask();

        subTaskData.deleteSubTask();
        subTaskData.createSubTask();

        sub_task_id = subTaskData.getSubTasks().get(0).getSub_task_id();
        assertNotNull(task_id);
        */
    }

    @AfterEach
    void destruct() throws ExecuteDeniedException {
        // After every test we will delete the test user.
        /*
        projectData.deleteProject();
        taskData.deleteTask();
        subTaskData.deleteSubTask();
        */
    }

    // TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
    void getSubTasks() {

    }

    @Test
    void getSubTask() {
    }

    @Test
    void createSubTask() {
    }

    @Test
    void editSubTask() {
    }
}