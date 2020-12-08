/**
 * @Author Rasmus Berg
 */

package com.example.demo.Data;

import com.example.demo.Domain.User;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.LoginException;
import com.example.demo.Exceptions.QueryDeniedException;
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
    public ArrayList<User> getUsers() throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add((User) userMapper.create(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public User getUser(int id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public User getUser(String e_mail) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM users WHERE e_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public User login(String email, String password) throws LoginException, QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM users WHERE e_mail=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (User) userMapper.create(resultSet);
        } catch (QueryDeniedException e) {
            throw new LoginException("password or credentials where incorrect");
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createUser(String e_mail, String password, String first_name, String last_name) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO users (e_mail, password, first_name, last_name) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, first_name);
            preparedStatement.setString(4, last_name);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when executing statement to database: SQLException message: " + e.getMessage());
        }
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

    public int findUserIdFromEmail(String e_mail) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT user_id from users where e_mail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, e_mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new QueryDeniedException("Error when querying resultSet: Empty resultset");
            }
            return resultSet.getInt("user_id");
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public String findEmailFromUserId(int id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT e_mail from users where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new QueryDeniedException("Error when querying resultSet: Empty resultset");
            }
            return resultSet.getString("e_mail");
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void uploadImg(int user_id, Blob img) throws ExecuteDeniedException {
        try {
            String statement = "UPDATE users SET img=? WHERE user_id=?;";
            Connection connection = connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setBlob(1, img);
            preparedStatement.setInt(2, user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteUser(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM users WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void deleteUser(String e_mail) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM users WHERE e_mail=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, e_mail);
        preparedStatement.executeUpdate();
    }

    public void setAdminStatus(int user_id, int is_admin) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "UPDATE users SET is_admin=? WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, is_admin);
        preparedStatement.setInt(2, user_id);
        preparedStatement.executeUpdate();
    }

    public void setPassword(int user_id, String password) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "UPDATE users SET password=? WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, password);
        preparedStatement.setInt(2, user_id);
        preparedStatement.executeUpdate();
    }

}
