package com.example.demo.Data;
/**
 * @author Adam Madsen
 * @version 1.0
 * @since 27-11-2020
 */

import com.example.demo.Domain.SubTask;
import com.example.demo.Domain.Task;
import com.example.demo.Exceptions.QueryDeniedException;

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
    public ArrayList<SubTask> getSubTasks(int task_id) throws SQLException, QueryDeniedException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM sub_tasks WHERE task_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, task_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<SubTask> tasks = new ArrayList<>();
        while (resultSet.next()) {
            tasks.add((Task) subTaskMapper.create(resultSet));
        }
        return tasks;
    }

    public SubTask getSubTask(int sub_task_id) throws SQLException, QueryDeniedException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM sub_tasks WHERE sub_task_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, sub_task_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (SubTask) subTaskMapper.create(resultSet);
    }

    public void createSubTask(int task_id, String sub_task_discription, int sub_task_id, String sub_task_name) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO sub_tasks (task_ID, sub_task_discription , sub_task_id, sub_task_name) VALUES (?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, task_id);
        preparedStatement.setString(2, sub_task_discription);
        preparedStatement.setInt(3, sub_task_id);
        preparedStatement.setString(4, sub_task_name);
        preparedStatement.execute();
    }

    public void editSubTask(int task_id, String sub_task_discription, int sub_task_id, String sub_task_name) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "UPDATE sub_tasks SET task_ID=?, sub_task_discription=?, sub_task_name=? WHERE sub_task_id=?;";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, task_id);
        preparedStatement.setString(2, sub_task_discription);
        preparedStatement.setString(3, sub_task_name);
        preparedStatement.setInt(4, sub_task_id);
        preparedStatement.executeUpdate();
    }
}

