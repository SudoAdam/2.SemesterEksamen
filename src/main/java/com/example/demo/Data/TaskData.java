package com.example.demo.Data;

/**
 *
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

import com.example.demo.Domain.Project;
import com.example.demo.Mapper.ProjectMapper;

import java.sql.*;
import java.util.ArrayList;

//stjålet fra projectData, skal rettes til task

public class TaskData {

        // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private final ProjectMapper projectMapper;
        private final Connector connector;

        // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public TaskData() {
            projectMapper = new ProjectMapper();
            connector = new Connector();
        }

        // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public ArrayList<Project> getProjects() {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM projects";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                ResultSet resultSet = preparedStatement.executeQuery();
                return (ArrayList) projectMapper.batch(resultSet);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return null;
        }

        public Project getProject(int id) {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM projects WHERE project_id=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return (Project) projectMapper.create(resultSet);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return null;
        }

        public boolean createProject(String project_name, Date kickoff, Date deadline, int project_leader_id, int customer_id) {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO projects (project_name, kickoff, deadline, project_leader_id, customer_id) VALUES (?,?,?,?,?)";
            boolean success = false;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setString(1, project_name);
                preparedStatement.setDate(2, kickoff);
                preparedStatement.setDate(3, deadline);
                preparedStatement.setInt(4, project_leader_id);
                preparedStatement.setInt(5, customer_id);
                preparedStatement.execute();
                success = true;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return success;
        }

        public boolean editProject(int project_id, String project_name, Date kickoff, Date deadline, int project_leader_id, int customer_id) {
            Connection connection = connector.getConnection();
            String statement = "UPDATE projects SET project_name=?, kickoff=?, deadline=?, project_leader_id=?, customer_id=? WHERE project_id=?";
            boolean success = false;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setString(1, project_name);
                preparedStatement.setDate(2, kickoff);
                preparedStatement.setDate(3, deadline);
                preparedStatement.setInt(4, project_leader_id);
                preparedStatement.setInt(5, customer_id);
                preparedStatement.setInt(6, project_id);
                preparedStatement.executeUpdate();
                success = true;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            return success;
        }
    }

