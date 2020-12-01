/**
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDataTest {
    private final ProjectData projectData = new ProjectData();

    @BeforeEach
    void init() {
        String project_name = "The newest Deal";
        Date kickoff = new Date(2020,12,02);
        Date deadline = new Date(2020,12,21);
        int project_leader = 1;
        int customer_id = 2;
        projectData.createProject(project_name, kickoff, deadline, project_leader, customer_id);
    }

    @Test
    void getProjects() {
        ArrayList<Project> list = projectData.getProjects();
        Project p = list.get(list.size()-1);

        // Assert correct table data
        assertEquals("The newest Deal", p.getProject_name());
        assertEquals(new Date(2020-12-02), p.getKickoff());
        assertEquals(new Date(2020-12-21), p.getDeadline());
        assertEquals(1, p.getProject_leader_id());
        assertEquals(2, p.getCustomer_id());

        // Assert domain object has correct type
        assertEquals(Project.class, list.get(0).getClass());
    }

    @Test
    void editProject() {
        // Select the last project in list and apply changes
        ArrayList<Project> list = projectData.getProjects();
        Project p = list.get(list.size()-1);

        int project_id = p.getProject_id();
        String project_name = "The newest Deal ADVANCED";
        Date kickoff = new Date(2020,12,10);
        Date deadline = new Date(2020,12,24);
        int project_leader = 1;
        int customer_id = 2;
        projectData.editProject(project_id, project_name, kickoff, deadline, project_leader, customer_id);

        ArrayList<Project> list_new = projectData.getProjects();
        Project p_new = list_new.get(list_new.size()-1);

        // Assert correct table data
        assertEquals("The newest Deal ADVANCED", p_new.getProject_name());
        assertEquals(new Date(2020,12,10), p_new.getKickoff());
        assertEquals(new Date(2020,12,24), p_new.getDeadline());
        assertEquals(1, p_new.getProject_leader_id());
        assertEquals(2, p_new.getCustomer_id());
    }
}