package com.example.demo.Service;

import com.example.demo.Data.ProjectData;
import com.example.demo.Data.UserData;
import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Project;
import com.example.demo.Domain.User;

import java.util.ArrayList;
import java.sql.Date;

public class ProjectService {

    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final UserData userData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectService() {
        this.projectData = new ProjectData();
        this.userData = new UserData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Project project) {
        int project_leader_id = project.getProject_leader_id();
        int customer_id = project.getCustomer_id();
        User project_leader = userData.getUser(project_leader_id);
        Customer customer = userData.getUser(customer_id);
        project.setProject_leader(project_leader);
        project.setCustomer(customer);
    }

    private void finalize(ArrayList<Project> list) {

    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> list = projectData.getProjects();
        finalize(list);
        return list;
    }

    public void createProject(String name, Date kickoff, Date deadline, int project_leader_id, int customer_id){
        projectData.createProject(name, kickoff, deadline, project_leader_id, customer_id);
    }
}
