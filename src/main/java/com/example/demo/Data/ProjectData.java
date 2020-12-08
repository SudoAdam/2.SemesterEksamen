/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Project;
import com.example.demo.Mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
    public ArrayList<Project> getProjects() throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM projects";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            projects.add((Project) projectMapper.create(resultSet));
        }
        return projects;
    }

    public Project getProject(int project_id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM projects WHERE project_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, project_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (Project) projectMapper.create(resultSet);
    }

    public void createProject(String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO projects (project_name, kickoff, deadline, project_leader_id, customer_id) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, project_name);
        preparedStatement.setString(2, kickoff.toString());
        preparedStatement.setString(3, deadline.toString());
        preparedStatement.setInt(4, project_leader_id);
        preparedStatement.setInt(5, customer_id);
        preparedStatement.execute();
    }

    public void editProject(int project_id, String project_name, LocalDate kickoff, LocalDate deadline, int project_leader_id, int customer_id) throws SQLException {
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
    }

    public void deleteProject(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM projects WHERE project_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
    public void assignUserToProject(int user_id, int project_id, int project_role_id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO project_participants (user_id, project_id, project_role_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, project_id);
        preparedStatement.setInt(3, project_role_id);
        preparedStatement.execute();
    }
}
