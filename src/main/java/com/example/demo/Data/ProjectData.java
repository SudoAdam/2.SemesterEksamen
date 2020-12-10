/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Project;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
import com.example.demo.Mapper.ProjectMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final ProjectMapper projectMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ProjectData(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
        this.connector = new Connector();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Project> getProjects() throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM projects";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                projects.add((Project) projectMapper.create(resultSet));
            }
            return projects;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public Project getProject(int project_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM projects WHERE project_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Project) projectMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public Project getProject(String project_name) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM projects WHERE project_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, project_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Project) projectMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public ArrayList<Project> getUserProjects(int user_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement =
                    "SELECT p.* " +
                    "FROM project_participants pp " +
                    "LEFT JOIN projects p " +
                    "ON p.project_id = pp.project_id " +
                    "WHERE pp.user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Project> projects = new ArrayList<>();
            while (resultSet.next()) {
                projects.add((Project) projectMapper.create(resultSet));
            }
            return projects;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createProject(String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO projects (project_name, kickoff, deadline, project_leader_id, customer_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, project_name);
            preparedStatement.setString(2, kickoff.toString());
            preparedStatement.setString(3, deadline.toString());
            preparedStatement.setInt(4, project_leader_id);
            preparedStatement.setInt(5, customer_id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void editProject(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "UPDATE projects SET project_name=?, kickoff=?, deadline=?, project_leader_id=?, customer_id=? WHERE project_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, project_name);
            preparedStatement.setString(2, kickoff.toString());
            preparedStatement.setString(3, deadline.toString());
            preparedStatement.setInt(4, project_leader_id);
            preparedStatement.setInt(5, customer_id);
            preparedStatement.setInt(6, project_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteProject(int id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM projects WHERE project_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteProject(String project_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM projects WHERE project_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, project_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }
}
