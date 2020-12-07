/**
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

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final UserData userData;
    private final CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectService(ProjectData projectData, UserData userData, CustomerData customerData) {
        this.projectData = projectData;
        this.userData = userData;
        this.customerData = customerData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Project project) throws SQLException {
        // Late dependency injection for single domain objects
        int project_leader_id = project.getProject_leader_id();
        int customer_id = project.getCustomer_id();
        User project_leader = userData.getUser(project_leader_id);
        Customer customer = customerData.getCustomer(customer_id);
        project.setProject_leader(project_leader);
        project.setCustomer(customer);
    }

    private void finalize(ArrayList<Project> list) throws SQLException {
        // Late dependency injection for collections of domain objects
        for (Project p : list) {
            finalize(p);
        }
    }

    public ArrayList<Project> getProjects() throws SQLException {
        ArrayList<Project> list = projectData.getProjects();
        // finalize(list);
        return list;
    }

    public Project getProject(int id) throws SQLException {
        Project p = projectData.getProject(id);
        finalize(p);
        return p;
    }

 /*   public ArrayList<Project> getUserProjects(User user) throws SQLException {
        ArrayList<Project> allProjectList = getProjects();
        ArrayList<Project> userProjectList = new ArrayList<>();

        if (user.getIs_admin() == 0) {
            for (Project p : allProjectList) {
                if (p.getEmployee_id == user.getUser_id()) {
                    userProjectList.add(p);
                }
            }
            return userProjectList;
        } else {
            return allProjectList;
        }
    } */

    public void createProject(String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws SQLException {
        projectData.createProject(project_name, kickoff, deadline, project_leader_id, customer_id);
    }

    public void editProject(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws SQLException {
        projectData.editProject(project_id, project_name, kickoff, deadline, project_leader_id, customer_id);
    }

    public void deleteProject(int id) throws SQLException {
        projectData.deleteProject(id);
    }
}
