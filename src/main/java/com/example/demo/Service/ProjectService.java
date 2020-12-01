/**
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Data.ProjectData;
import com.example.demo.Data.UserData;
import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Project;
import com.example.demo.Domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class ProjectService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final UserData userData;
    private final CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectService() {
        this.projectData = new ProjectData();
        this.userData = new UserData();
        this.customerData = new CustomerData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Project project) {
        // Late dependency injection for single domain objects
        int project_leader_id = project.getProject_leader_id();
        int customer_id = project.getCustomer_id();
        User project_leader = userData.getUser(project_leader_id);
        Customer customer = customerData.getCustomer(customer_id);
        project.setProject_leader(project_leader);
        project.setCustomer(customer);
    }

    private void finalize(ArrayList<Project> list) {
        // Late dependency injection for collections of domain objects
        for (Project p: list) {
            finalize(p);
        }
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> list = projectData.getProjects();
        finalize(list);
        return list;
    }

    public Project getProject(int id) {
        Project p = projectData.getProject(id);
        finalize(p);
        return p;
    }

    public boolean createProject(String name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id){
        return projectData.createProject(name, kickoff, deadline, project_leader_id, customer_id);
    }

    public boolean editProject(int project_id, String name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) {
        return projectData.editProject(project_id, name, kickoff, deadline, project_leader_id, customer_id);
    }
}
