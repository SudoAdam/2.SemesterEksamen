/**
 * This test is specifically made ordered.
 * Integration test of data layer
 *
 * (Deprecated due to changes in database with cascade constraint)
 *
 * @author Patrick Vincent Højstrøm
 * @since 27-11-2020
 */
package com.example.demo.Data;

import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskDataTest {
    private final TaskData taskData;
    private final ProjectData projectData;
    private final CustomerData customerData;
    private final UserData userData;

    private final String project_leader_mail;
    private final String customer_mail;
    private int project_leader_id;
    private int customer_id;
    private int project_id;

    private int task_id;
    private final String task_name;
    private final String task_description;
    private final LocalDate kickoff;
    private final LocalDate deadline;

    TaskDataTest(ApplicationContext ctx) {
        this.taskData = (TaskData) ctx.getBean("taskData");
        this.projectData = (ProjectData) ctx.getBean("projectData");
        this.customerData = (CustomerData) ctx.getBean("customerData");
        this.userData = (UserData) ctx.getBean("userData");

        this.project_leader_mail = "Mock@mail.com";
        this.customer_mail = "Mock@mail.com";

        this.task_name = "Make clean!";
        this.task_description = "The software has a lot of mess in it...";
        this.kickoff = LocalDate.of(2020,12,2);
        this.deadline = LocalDate.of(2020,12,8);
    }

    void assertTask(Task t) {
        assertEquals(project_id,t.getProject_id());
        assertEquals(task_name,t.getTask_name());
        assertEquals(task_description,t.getTask_description());
        assertEquals(project_leader_id,t.getTask_leader_id());
        assertEquals(kickoff,t.getKickoff());
        assertEquals(deadline,t.getDeadline());
    }

    @BeforeEach
    void construct() throws OperationDeniedException, EmptyResultSetException, QueryDeniedException {
        try {
            customerData.createCustomer("MockCustomer", "MockName", customer_mail, "12121212");
        } catch (OperationDeniedException e) {
            projectData.deleteProject("Mock");
            customerData.deleteCustomer(customer_mail);
            userData.deleteUser(project_leader_mail);
            customerData.createCustomer("MockCustomer", "MockName", customer_mail, "12121212");
        }
        userData.createUser(project_leader_mail, "mock", "Sammy", "Jonson");

        customer_id = customerData.getCustomer(customer_mail).getCustomer_id();
        project_leader_id = userData.getUser(project_leader_mail).getUser_id();

        projectData.createProject("Mock", kickoff, deadline, project_leader_id, customer_id);
        project_id = projectData.getProject("Mock").getProject_id();

        try {
            taskData.createTask(project_id, task_name, task_description, project_leader_id, kickoff, deadline);
        } catch (OperationDeniedException e) {
            taskData.deleteTask(task_name);
            taskData.createTask(project_id, task_name, task_description, project_leader_id, kickoff, deadline);
        }
        ArrayList<Task> list = taskData.getTasks(project_id);
        task_id = list.get(list.size()-1).getTask_id();
    }

    @AfterEach
    void destruct() throws OperationDeniedException {
        projectData.deleteProject(project_id);
        customerData.deleteCustomer(customer_mail);
        userData.deleteUser(project_leader_mail);
        taskData.deleteTask(task_id);
    }

    @Test
    void getTasks() throws QueryDeniedException, EmptyResultSetException {
        ArrayList<Task> list = taskData.getTasks(project_id);
        Task t = list.get(list.size()-1);
        assertTask(t);
    }

    @Test
    void getTask() throws EmptyResultSetException, QueryDeniedException {
        Task t = taskData.getTask(task_id);
        assertTask(t);
    }

    @Test
    void editTask() throws QueryDeniedException, OperationDeniedException, EmptyResultSetException {

        String desc = "New Description";
        taskData.editTask(task_id, project_id, task_name, desc, project_leader_id, kickoff, deadline);

        Task t = taskData.getTask(task_id);
        assertEquals(desc, t.getTask_description());
    }
}