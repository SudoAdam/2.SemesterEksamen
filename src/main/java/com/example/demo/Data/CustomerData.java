/**
 * @Author Rasmus Berg
 */


package com.example.demo.Data;

import com.example.demo.Domain.Customer;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;
import com.example.demo.Mapper.CustomerMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerData {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private final CustomerMapper customerMapper;
    private final Connector connector;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public CustomerData(CustomerMapper customerMapper, Connector connector) {
        this.customerMapper = customerMapper;
        this.connector = connector;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Customer> getCustomers() throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM customers";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add((Customer) customerMapper.create(resultSet));
            }
            return customers;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public Customer getCustomer(int id) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "SELECT * FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Customer) customerMapper.create(resultSet);
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void createCustomer(String name, String contact_name, String contact_email, String contact_phone) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "INSERT INTO customers (name, contact_name, contact_email, contact_phone) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, contact_name);
            preparedStatement.setString(3, contact_email);
            preparedStatement.setString(4, contact_phone);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone) throws QueryDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "UPDATE customers SET name=?, contact_name=?, contact_email=?, contact_phone=? WHERE customer_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, contact_name);
            preparedStatement.setString(3, contact_email);
            preparedStatement.setString(4, contact_phone);
            preparedStatement.setInt(5, customer_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public int findCustomerIdFromName(String name) throws QueryDeniedException {
        try {
            int id = -1;
            Connection connection = connector.getConnection();
            String statement = "SELECT customer_id from customers where name like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, '"' + name + '"');
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "" + resultSet.getObject(1);
            id = Integer.parseInt(result);
            return id;
        } catch (SQLException e) {
            throw new QueryDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }

    public void deleteCustomer(int id) throws ExecuteDeniedException {
        try {
            Connection connection = connector.getConnection();
            String statement = "DELETE FROM customers WHERE customer_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExecuteDeniedException("Error when querying database: SQLException message: " + e.getMessage());
        }
    }
}
