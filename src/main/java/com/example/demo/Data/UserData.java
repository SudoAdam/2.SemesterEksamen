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
    public UserData(UserMapper userMapper, Connector connector) {
        this.userMapper = userMapper;
        this.connector = connector;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<User> getUsers() throws SQLException{
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add((User) userMapper.create(resultSet));
        }
        return users;
    }

    public User getUser(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (User) userMapper.create(resultSet);
    }

    public User login(String email, String password) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM users WHERE e_mail=? and password=?";

        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (User) userMapper.create(resultSet);
    }

    public void createUser(String e_mail, String password, String first_name, String last_name) throws SQLException{
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO users (e_mail, password, first_name, last_name) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, e_mail);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, first_name);
        preparedStatement.setString(4, last_name);
        preparedStatement.execute();
    }

    public void editUser(int user_id, String e_mail, String password, String first_name, String last_name, int is_admin) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "UPDATE users SET e_mail=?, password=?, first_name=?, last_name=?, is_admin=? WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, e_mail);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, first_name);
        preparedStatement.setString(4, last_name);
        preparedStatement.setInt(5, is_admin);
        preparedStatement.setInt(6, user_id);
        preparedStatement.executeUpdate();
    }

    public int findUserIdFromEmail(String e_mail) throws SQLException{
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT user_id from users where e_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = "" + resultSet.getObject(1);
            id = Integer.parseInt(result);
        return id;

    }

    public String findEmailFromUserId(int id) throws SQLException{
        String e_mail = "";
        Connection connection = connector.getConnection();
        String statement = "SELECT e_mail from users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = "" + resultSet.getString("e_mail");
            e_mail = result;
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
    public void deleteUser(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM users WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
