/**
 * Integration test of data layer
 *
 * (Deprecated due to changes in database with cascade constraint)
 *
 * @author Patrick
 * @since 9-12-2020
 */
package com.example.demo.Data;

import com.example.demo.Domain.SubTask;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Deprecated
@SpringBootTest
class SubTaskDataTest {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final TaskData taskData;
    private final SubTaskData subTaskData;
    private final UserData userData;
    private final CustomerData customerData;
    private int project_id;
    private int task_id;
    private int sub_task_id;
    private int leader_id;
    private int customer_id;
    private final String project_name;
    private final String task_name;
    private final String sub_task_description;
    private final String sub_task_name;
    private final int sub_task_hrs;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SubTaskDataTest(ApplicationContext ctx) {
        this.projectData = (ProjectData) ctx.getBean("projectData");
        this.taskData = (TaskData) ctx.getBean("taskData");
        this.subTaskData = (SubTaskData) ctx.getBean("subTaskData");
        this.userData = (UserData) ctx.getBean("userData");
        this.customerData = (CustomerData) ctx.getBean("customerData");
        this.project_name = "SubTask test project";
        this.task_name = "SubTask test task";
        this.sub_task_description = "testing of sub task";
        this.sub_task_name = "SubTask test";
        this.sub_task_hrs = 14;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void assertSubTask(SubTask subTask) {
        assertEquals(task_id, subTask.getTask_id());
        assertEquals(sub_task_id, subTask.getSub_task_id());
        assertEquals(sub_task_description, subTask.getSub_task_description());
        assertEquals(sub_task_name, subTask.getSub_task_name());
    }

    @BeforeEach
    void construct() throws QueryDeniedException, OperationDeniedException, EmptyResultSetException {

        userData.createUser("mock@user.com", "mockPass", "mock", "mocky");
        leader_id = userData.getUser("mock@user.com").getUser_id();

        customerData.createCustomer("Company", "mock", "mock@customer.com", "12345678");
        customer_id = customerData.getCustomer("mock@customer.com").getCustomer_id();

        projectData.createProject(
                project_name,
                LocalDate.of(2020,1,1),
                LocalDate.of(2020,1,2),
                leader_id,
                customer_id
        );
        project_id = projectData.getProject(project_name).getProject_id();

        taskData.createTask(
                project_id,
                task_name,
                "test for sub task",
                leader_id,
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2020,1,2)
        );
        task_id = taskData.getTasks(project_id).get(0).getTask_id();

        subTaskData.createSubTask(task_id, sub_task_description, sub_task_name, sub_task_hrs);
        sub_task_id = subTaskData.getSubTask(task_id, sub_task_name).getSub_task_id();
    }

    @AfterEach
    void destruct() throws OperationDeniedException {
        userData.deleteUser(leader_id);
        customerData.deleteCustomer(customer_id);
        projectData.deleteProject(project_id);
        taskData.deleteTask(task_id);
        subTaskData.deleteSubTask(task_id, sub_task_id);
    }

    // TEST ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Test
    void getSubTasks() throws QueryDeniedException, EmptyResultSetException {
        ArrayList<SubTask> list = subTaskData.getSubTasks(project_id);
        assertSubTask(list.get(0));
    }

    @Test
    void getSubTask() throws QueryDeniedException, EmptyResultSetException {
        SubTask subTask = subTaskData.getSubTask(task_id, sub_task_id);
        assertSubTask(subTask);
    }

}