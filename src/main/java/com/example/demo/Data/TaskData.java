/**
 * @author Patrick Vincent Højstrøm
 * @version 1.0
 * @since 27-11-2020
 */

package com.example.demo.Data;

import com.example.demo.Domain.Task;
import com.example.demo.Mapper.TaskMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final TaskMapper taskMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public TaskData() {
        taskMapper = new TaskMapper();
        connector = new Connector();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Task> getTasks(int project_id) {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM tasks WHERE project_id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (ArrayList) taskMapper.batch(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean createTask(int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO tasks (project_id, task_name, task_description, task_leader_id, kickoff, deadline, working_hours) VALUES (?,?,?,?,?,?,?);";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, project_id);
            preparedStatement.setString(2, task_name);
            preparedStatement.setString(3, task_description);
            preparedStatement.setInt(4, task_leader_id);
            preparedStatement.setString(5, kickoff.toString());
            preparedStatement.setString(6, deadline.toString());
            preparedStatement.setInt(7, working_hours);
            preparedStatement.execute();
            success = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }

    public boolean editTask(int task_id, int project_id, String task_name, String task_description, int task_leader_id, LocalDate kickoff, LocalDate deadline, int working_hours) {
        Connection connection = connector.getConnection();
        String statement = "UPDATE tasks SET project_id=?, task_name=?, task_description=?, task_leader_id=?, kickoff=?, deadline=?, working_hours=? WHERE task_id=?;";
        boolean success = false;
        try {
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
            success = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }
}

