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

import com.example.demo.Domain.Project;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ProjectDataTest {
    private final ProjectData projectData;
    private final CustomerData customerData;
    private final UserData userData;

    private final String project_name;
    private final String project_name_new;
    private final LocalDate kickoff;
    private final LocalDate deadline;
    private final String project_leader_mail;
    private final String customer_mail;

    ProjectDataTest(ApplicationContext ctx) {
        this.projectData = (ProjectData) ctx.getBean("projectData");
        this.customerData = (CustomerData) ctx.getBean("customerData");
        this.userData = (UserData) ctx.getBean("userData");
        this.project_name = "The newest Deal";
        this.project_name_new = "The newest Deal ADVANCED";
        this.kickoff = LocalDate.of(2020,12,2);
        this.deadline = LocalDate.of(2020,12,21);
        this.project_leader_mail = "MockUser@Mail.com";
        this.customer_mail = "Mock@Mail.com";
    }

    void assertProject(Project p) {
        assertEquals(project_name, p.getProject_name());
        assertEquals(kickoff, p.getKickoff());
        assertEquals(deadline, p.getDeadline());
    }

    @Test
    @Order(0)
    void createProject() {

        try {
            // Projects are dependent on a customer
            customerData.createCustomer("MockCustomer", "MockName", customer_mail, "12121212");
        } catch (OperationDeniedException e) {
            assert true;
        }
        try {
            // Projects are dependent on a user
            userData.createUser(project_leader_mail, "mock", "Sammy", "Jonson");
        } catch (OperationDeniedException e) {
            assert true;
        }
        try {
            int customer_id = customerData.getCustomer(customer_mail).getCustomer_id();
            int project_leader_id = userData.getUser(project_leader_mail).getUser_id();
            projectData.createProject(project_name, kickoff, deadline, project_leader_id, customer_id);
        } catch (OperationDeniedException | QueryDeniedException | EmptyResultSetException e) {
            assert true;
        }

    }

    @Test
    @Order(1)
    void getProjects() throws QueryDeniedException, EmptyResultSetException {
        // Get list of projects
        ArrayList<Project> list = projectData.getProjects();

        // Last project should be the test project
        Project p = list.get(list.size()-1);

        assertProject(p);
    }

    @Test
    @Order(2)
    void getProject() throws QueryDeniedException, EmptyResultSetException {
        Project p = projectData.getProject(project_name);

        assertProject(p);
    }

    @Test
    @Order(3)
    void editProject() throws QueryDeniedException, OperationDeniedException, EmptyResultSetException {
        projectData.editProject(project_name, project_name_new, kickoff, deadline);

        Project p_new = projectData.getProject(project_name_new);

        // Assert that the old information evaluated in assertProject() is no longer valid
        assertThrows(AssertionError.class, ()->{assertProject(p_new);});

        // Update assertProject() and validate that information is now correct
        assertEquals(project_name_new, p_new.getProject_name());
        assertEquals(kickoff, p_new.getKickoff());
        assertEquals(deadline, p_new.getDeadline());
    }

    @Test
    @Order(4)
    void deleteProject() throws OperationDeniedException, QueryDeniedException, EmptyResultSetException {
        try {
            projectData.getProject(project_name);
            projectData.deleteProject(project_name);
        } catch (EmptyResultSetException e) {
            projectData.getProject(project_name_new);
            projectData.deleteProject(project_name_new);
        }

        customerData.deleteCustomer(customer_mail);
        userData.deleteUser(project_leader_mail);

        assertThrows(EmptyResultSetException.class, ()->{projectData.getProject(project_name);});
    }
}