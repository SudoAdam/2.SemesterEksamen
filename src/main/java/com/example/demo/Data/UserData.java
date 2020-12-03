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
            return (ArrayList) userMapper.create(resultSet);
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

    public boolean createUser(String e_mail, String password, String first_name, String last_name, int job_title_id){
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO users (e_mail, password, first_name, last_name, job_title_id) VALUES (?,?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setInt(5, job_title_id);
            preparedStatement.execute();
            success = true;
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }

    public boolean editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin, int project_role_id){
        Connection connection = connector.getConnection();
        String statement = "UPDATE users SET e_mail=?, password=?, first_name=?, last_name=?, is_admin=?, project_role_id=? WHERE user_id=?";
        boolean success = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setInt(5, is_admin);
            preparedStatement.setInt(6, project_role_id);
            preparedStatement.setInt(7, user_id);
            preparedStatement.executeUpdate();
            success = true;
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }

    public  int findUserIdFromEmail (String e_mail){
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT user_id from users where e_mail = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            String result ="" + resultSet.getObject(1);
            id = Integer.parseInt(result);

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return id;

    }
}
