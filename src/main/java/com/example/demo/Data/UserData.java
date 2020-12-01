package com.example.demo.Data;

import com.example.demo.Domain.Project;
import com.example.demo.Domain.User;
import com.example.demo.Mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class UserData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final UserMapper userMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public UserData() {
        userMapper = new UserMapper();
        connector = new Connector();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers() {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (ArrayList) userMapper.batch(resultSet);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public User getUser(int id){
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users WHERE user_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean createUser(String firstName, String lastName, String email, String projects, int isAdmin, String jobTitle){
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO User (firstName, lastName, email, projects, isAdmin, jobTitle) VALUES (?,?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, projects);
            preparedStatement.setInt(5, isAdmin);
            preparedStatement.setString(6, jobTitle);
            preparedStatement.execute();
            success = true;
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }
}
