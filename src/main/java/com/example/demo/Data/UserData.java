/**
 * @Author Rasmus Berg
 */


package com.example.demo.Data;

import com.example.demo.Domain.User;
import com.example.demo.Mapper.UserMapper;

import java.sql.*;
import java.util.ArrayList;

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

            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add((User) userMapper.create(resultSet));
            }
            return users;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public User getUser(int id) {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public User login(String email, String password) {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users WHERE e_mail=? and password=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean createUser(String e_mail, String password, String first_name, String last_name) {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO users (e_mail, password, first_name, last_name) VALUES (?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.execute();
            success = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }

    public boolean editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin) {
        Connection connection = connector.getConnection();
        String statement = "UPDATE users SET e_mail=?, password=?, first_name=?, last_name=?, is_admin=? WHERE user_id=?";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.setInt(5, is_admin);
            preparedStatement.setInt(6, user_id);
            preparedStatement.executeUpdate();
            success = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return success;
    }

    public int findUserIdFromEmail(String e_mail) {
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT user_id from users where e_mail = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = "" + resultSet.getObject(1);
            id = Integer.parseInt(result);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return id;

    }

    public String findEmailFromUserId(int id) {
        String e_mail = "";
        Connection connection = connector.getConnection();
        String statement = "SELECT e_mail from users where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = "" + resultSet.getString("e_mail");
            e_mail = result;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return e_mail;

    }

    public void uploadImg(int user_id, Blob img) {
        String statement = "UPDATE users SET img=? WHERE user_id=?;";
        Connection connection = connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setBlob(1, img);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new NullPointerException(sqlException.getMessage());
        }
    }

}
