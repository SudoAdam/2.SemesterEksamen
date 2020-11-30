package com.example.demo.Service;

import com.example.demo.Data.ProjectData;
import com.example.demo.Domain.Project;

import java.util.ArrayList;
import java.sql.Date;

public class ProjectService {

    private ProjectData projectData;

    public ProjectService() {
        projectData = new ProjectData();
    }

    public ArrayList<Project> getProjects() {
        return projectData.getProjects();
    }

    public void createProject(String name, Date kickoff, Date deadline, int project_leader_id, int customer_id){
        projectData.createProject(name, kickoff, deadline, project_leader_id, customer_id);
    }
}
