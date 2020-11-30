package com.example.demo.Data;

import com.example.demo.Domain.Project;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDataTest {

    @Test
    void getProjects() {
        ProjectData projectData = new ProjectData();
        ArrayList<Project> list = projectData.getProjects();

        // Assert project is correct by name
        assertEquals("The new Deal", list.get(0).getName());

        // Assert project has correct type
        assertEquals(Project.class, list.get(0).getClass());
    }

    @Test
    void createProject() {
        ProjectData projectData = new ProjectData();
        String project_name = "This new Thing";
        Date kickoff = new Date(2020,12,2);
        Date deadline = new Date(2020,12,21);
        int project_leader = 1;
        int customer_id = 2;
        boolean result = projectData.createProject(project_name, kickoff, deadline, project_leader, customer_id);
        assertEquals(true, result);
    }
}