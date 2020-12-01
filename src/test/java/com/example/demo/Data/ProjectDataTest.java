/**
 * This test is specifically made ordered.
 * Ordered tests are needed when testing data requests due to CRUD (Create, Read, Edit, Delete)
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Project;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectDataTest {
    private final ProjectData projectData = new ProjectData();

    @Test
    @Order(0)
    void createProject() {
        String project_name = "The newest Deal";
        LocalDate kickoff = LocalDate.of(2020,12,2);
        LocalDate deadline = LocalDate.of(2020,12,21);
        int project_leader = 1;
        int customer_id = 2;
        projectData.createProject(project_name, kickoff, deadline, project_leader, customer_id);
    }

    @Test
    @Order(1)
    void getProjects() {
        ArrayList<Project> list = projectData.getProjects();
        Project p = list.get(list.size()-1);

        // Assert correct table data
        assertEquals("The newest Deal", p.getProject_name());
        assertEquals(LocalDate.of(2020,12, 2), p.getKickoff());
        assertEquals(LocalDate.of(2020,12,21), p.getDeadline());
        assertEquals(1, p.getProject_leader_id());
        assertEquals(2, p.getCustomer_id());

        // Assert domain object has correct type
        assertEquals(Project.class, list.get(0).getClass());
    }

    @Test
    @Order(2)
    void getProject() {
        // For the sake of test, we need the project for the last row in the DBMS
        ArrayList<Project> list = projectData.getProjects();

        // Actual method test
        Project p = projectData.getProject(list.get(list.size()-1).getProject_id());

        // Assert correct table data
        assertEquals("The newest Deal", p.getProject_name());
        assertEquals(LocalDate.of(2020,12, 2), p.getKickoff());
        assertEquals(LocalDate.of(2020,12,21), p.getDeadline());
        assertEquals(1, p.getProject_leader_id());
        assertEquals(2, p.getCustomer_id());

        // Assert domain object has correct type
        assertEquals(Project.class, list.get(0).getClass());
    }

    @Test
    @Order(3)
    void editProject() {
        // Select the last project in list and apply changes
        ArrayList<Project> list = projectData.getProjects();
        Project p = list.get(list.size()-1);

        int project_id = p.getProject_id();
        String project_name = "The newest Deal ADVANCED";
        LocalDate kickoff = LocalDate.of(2020,12,10);
        LocalDate deadline = LocalDate.of(2020,12,24);
        int project_leader = 1;
        int customer_id = 2;
        projectData.editProject(project_id, project_name, kickoff, deadline, project_leader, customer_id);

        ArrayList<Project> list_new = projectData.getProjects();
        Project p_new = list_new.get(list_new.size()-1);

        // Assert correct table data
        assertEquals("The newest Deal ADVANCED", p_new.getProject_name());
        assertEquals(LocalDate.of(2020,12,10), p_new.getKickoff());
        assertEquals(LocalDate.of(2020,12,24), p_new.getDeadline());
        assertEquals(1, p_new.getProject_leader_id());
        assertEquals(2, p_new.getCustomer_id());
    }


}