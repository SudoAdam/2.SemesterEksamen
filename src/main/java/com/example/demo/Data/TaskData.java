/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
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
    public ArrayList<Task> getTasks(int project_id) throws QueryDeniedException, EmptyResultSetException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM tasks INNER JOIN users ON tasks.task_leader_id=users.user_id where project_id = ?";
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

    public Task getTask(int task_id) throws QueryDeniedException, EmptyResultSetException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM tasks INNER JOIN users ON tasks.task_leader_id=users.user_id WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Task) taskMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline) throws OperationDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO tasks (project_id, task_name, task_description, task_leader_id, kickoff, deadline) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            preparedStatement.setString(2, task_name);
            preparedStatement.setString(3, task_description);
            preparedStatement.setInt(4, task_leader_id);
            preparedStatement.setString(5, kickoff.toString());
            preparedStatement.setString(6, deadline.toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new OperationDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline) throws OperationDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "UPDATE tasks SET project_id=?, task_name=?, task_description=?, task_leader_id=?, kickoff=?, deadline=? WHERE task_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            preparedStatement.setString(2, task_name);
            preparedStatement.setString(3, task_description);
            preparedStatement.setInt(4, task_leader_id);
            preparedStatement.setString(5, kickoff.toString());
            preparedStatement.setString(6, deadline.toString());
            preparedStatement.setInt(7, task_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteTask(int id) throws OperationDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM tasks WHERE task_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteTask(String task_name) throws OperationDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM tasks WHERE task_name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, task_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new OperationDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }
}

