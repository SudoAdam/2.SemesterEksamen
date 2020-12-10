/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
import com.example.demo.Mapper.TaskMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskMapper taskMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskData(TaskMapper taskMapper, Connector connector) {
        this.taskMapper = taskMapper;
        this.connector = connector;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Task> getTasks(int project_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM tasks WHERE project_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add((Task) taskMapper.create(resultSet));
            }
            return tasks;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public Task getTask(int task_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM tasks WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Task) taskMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO tasks (project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            preparedStatement.setString(2, task_name);
            preparedStatement.setString(3, task_description);
            preparedStatement.setInt(4, task_leader_id);
            preparedStatement.setString(5, kickoff.toString());
            preparedStatement.setString(6, deadline.toString());
            preparedStatement.setInt(7, working_hours);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "UPDATE tasks SET project_id=?, task_name=?, task_description=?, task_leader_id=?, kickoff=?, deadline=?, working_hours=? WHERE task_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            preparedStatement.setString(2, task_name);
            preparedStatement.setString(3, task_description);
            preparedStatement.setInt(4, task_leader_id);
            preparedStatement.setString(5, kickoff.toString());
            preparedStatement.setString(6, deadline.toString());
            preparedStatement.setInt(7, working_hours);
            preparedStatement.setInt(8, task_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteTask(int id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM tasks WHERE task_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteTask(String task_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM tasks WHERE task_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, task_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }
}

