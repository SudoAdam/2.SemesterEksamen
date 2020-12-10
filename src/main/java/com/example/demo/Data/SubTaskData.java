package com.example.demo.Data;
/**
 * @author Adam Madsen
 * @version 1.0
 * @since 09-12-2020
 */

import com.example.demo.Domain.SubTask;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
import com.example.demo.Mapper.SubTaskMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubTaskData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final SubTaskMapper subTaskMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public SubTaskData(SubTaskMapper subTaskMapper, Connector connector) {
        this.subTaskMapper = subTaskMapper;
        this.connector = connector;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<SubTask> getSubTasks(int project_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement =
                    "SELECT s.* " +
                            "FROM projects p " +
                            "LEFT JOIN tasks t ON p.project_id = t.project_id " +
                            "LEFT JOIN sub_tasks s ON t.task_id = s.task_id " +
                            "WHERE p.project_id = ? " +
                            "ORDER BY sub_task_id;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<SubTask> subTasks = new ArrayList<>();
            while (resultSet.next()) {
                subTasks.add((SubTask) subTaskMapper.create(resultSet));
            }
            return subTasks;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public SubTask getSubTask(int task_id, int sub_task_id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM sub_tasks WHERE task_id = ? AND sub_task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            preparedStatement.setInt(2, sub_task_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (SubTask) subTaskMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public SubTask getSubTask(int task_id, String sub_task_name) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM sub_tasks WHERE task_id = ? AND sub_task_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            preparedStatement.setString(2, sub_task_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (SubTask) subTaskMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createSubTask(int task_id, String sub_task_description, String sub_task_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO sub_tasks (task_ID, sub_task_description, sub_task_name) VALUES (?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            preparedStatement.setString(2, sub_task_description);
            preparedStatement.setString(3, sub_task_name);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void editSubTask(int task_id, String sub_task_description, int sub_task_id, String sub_task_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "UPDATE sub_tasks SET task_ID=?, sub_task_description=?, sub_task_name=? WHERE task_id=? AND sub_task_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            preparedStatement.setString(2, sub_task_description);
            preparedStatement.setString(3, sub_task_name);
            preparedStatement.setInt(4, task_id);
            preparedStatement.setInt(5, sub_task_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteSubTask(int task_id, int sub_task_id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "" +
                    "DELETE FROM sub_tasks " +
                    "WHERE task_id = ? AND sub_task_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, task_id);
            preparedStatement.setInt(2, sub_task_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteSubTask(String task_name, String sub_task_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "" +
                    "DELETE s.* FROM tasks t " +
                    "LEFT JOIN sub_tasks s ON t.task_id = s.task_id " +
                    "WHERE t.task_name = ? AND s.sub_task_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, task_name);
            preparedStatement.setString(2, sub_task_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when requesting database: SQLException message: " + e.getMessage());
        }
    }
}

