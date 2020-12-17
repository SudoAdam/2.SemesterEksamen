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
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import com.example.demo.Exceptions.ServiceExceptions.DateContextException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectData projectData;
    private final UserData userData;
    private final CustomerData customerData;
    private final ParticipantData participantData;
    private final DateLogic dateLogic;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectService(ProjectData projectData, UserData userData, CustomerData customerData, ParticipantData participantData, DateLogic dateLogic) {
        this.projectData = projectData;
        this.userData = userData;
        this.customerData = customerData;
        this.participantData = participantData;
        this.dateLogic = dateLogic;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public ArrayList<Project> getProjects() throws FailedRequestException {
        try {
            ArrayList<Project> list = projectData.getProjects();
            return list;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public Project getProject(int project_id) throws FailedRequestException {
        try {
            Project project = projectData.getProject(project_id);

            int project_leader_id = project.getProject_leader_id();
            int customer_id = project.getCustomer_id();
            User project_leader = userData.getUser(project_leader_id);
            Customer customer = customerData.getCustomer(customer_id);
            project.setProject_leader(project_leader);
            project.setCustomer(customer);

            return project;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public ArrayList<Project> getUserProjects(int user_id) throws FailedRequestException {
        try {
            return projectData.getUserProjects(user_id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void createProject(String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws FailedRequestException, DateContextException {
        try {
            if (!dateLogic.correctDate(kickoff, deadline)) {
                throw new DateContextException();
            }
            projectData.createProject(project_name, kickoff, deadline, project_leader_id, customer_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void editProject(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws FailedRequestException {
        try {
        projectData.editProject(project_id, project_name, kickoff, deadline, project_leader_id, customer_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteProject(int project_id) throws FailedRequestException {
        try {
        projectData.deleteProject(project_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void assignParticipant(int user_id, int project_id, int project_role_id) throws FailedRequestException {
        try {
        participantData.assignParticipant(user_id, project_id, project_role_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public ArrayList<Participant> getParticipants(int project_id) throws FailedRequestException {
        try {
        ArrayList<Participant> list = participantData.getParticipants(project_id);
        return list;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    /*
    public Participant getParticipant(int user_id, int project_id) throws QueryDeniedException {
        // Not working yet
        Participant p = participantData.getProjectParticipant(user_id, project_id);
        return p;
    }
    */

    public void removeParticipant(int user_id, int project_id) throws FailedRequestException {
        try {
            participantData.removeParticipant(user_id, project_id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
