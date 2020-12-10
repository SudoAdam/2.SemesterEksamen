/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Data.ParticipantData;
import com.example.demo.Data.ProjectData;
import com.example.demo.Data.UserData;
import com.example.demo.Domain.Customer;
import com.example.demo.Domain.Participant;
import com.example.demo.Domain.Project;
import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final UserData userData;
    private final CustomerData customerData;
    private final ParticipantData participantData;


    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectService(ProjectData projectData, UserData userData, CustomerData customerData, ParticipantData participantData) {
        this.projectData = projectData;
        this.userData = userData;
        this.customerData = customerData;
        this.participantData = participantData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void finalize(Project project) throws QueryDeniedException {
        // Late dependency injection for single domain objects
        int project_leader_id = project.getProject_leader_id();
        int customer_id = project.getCustomer_id();
        User project_leader = userData.getUser(project_leader_id);
        Customer customer = customerData.getCustomer(customer_id);
        project.setProject_leader(project_leader);
        project.setCustomer(customer);
    }

    private void finalize(ArrayList<Project> list) throws QueryDeniedException {
        // Late dependency injection for collections of domain objects
        for (Project p : list) {
            finalize(p);
        }
    }

    public ArrayList<Project> getProjects() throws QueryDeniedException {
        ArrayList<Project> list = projectData.getProjects();
        // finalize(list);
        return list;
    }

    public Project getProject(int id) throws QueryDeniedException {
        Project p = projectData.getProject(id);
        finalize(p);
        return p;
    }

    private boolean correctDate(LocalDate kickoff, LocalDate deadline) {
        boolean result = false;
        if (kickoff.isBefore(deadline)) {
            result = true;
        }
        return result;
    }

    public ArrayList<Project> getUserProjects(int user_id) throws QueryDeniedException {
        return projectData.getUserProjects(user_id);
    }

    public void createProject(String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws Exception {
        if (correctDate(kickoff, deadline) == false) {
            throw new Exception();
        }
        projectData.createProject(project_name, kickoff, deadline, project_leader_id, customer_id);
    }

    public void editProject(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws ExecuteDeniedException {
        projectData.editProject(project_id, project_name, kickoff, deadline, project_leader_id, customer_id);
    }

    public void deleteProject(int id) throws ExecuteDeniedException {
        projectData.deleteProject(id);
    }

    public void assignParticipant(int user_id, int project_id, int project_role_id) throws ExecuteDeniedException {
        participantData.assignParticipant(user_id, project_id, project_role_id);
    }

    public ArrayList<Participant> getParticipants(int project_id) throws QueryDeniedException {
        ArrayList<Participant> list = participantData.getParticipants(project_id);
        return list;
    }

    public Participant getParticipant(int user_id, int project_id) throws QueryDeniedException {
        // Not working yet
        Participant p = participantData.getProjectParticipant(user_id, project_id);
        return p;
    }

}
