package com.example.demo.Data;

import com.example.demo.Domain.Project;
import com.example.demo.Mapper.ProjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class ProjectData {

    ProjectMapper projectMapper;
    Connector connector;

    public ProjectData() {
        projectMapper = new ProjectMapper();
        connector = new Connector();
    }

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

    public boolean createProject(String project_name, Date kickoff, Date deadline, int project_leader_id, int customer_id) {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO projects (project_name, kickoff, deadline, project_leader_id, customer_id) VALUES (?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,project_name);
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

}
