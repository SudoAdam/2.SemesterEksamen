/**
 * @Author Rasmus Berg
 */


package com.example.demo.Data;

import com.example.demo.Domain.Customer;
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
    public ArrayList<Customer> getCustomers() throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM customers";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add((Customer) customerMapper.create(resultSet));
        }
        return customers;
    }

    public Customer getCustomer(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM customers WHERE customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (Customer) customerMapper.create(resultSet);
    }

    public void createCustomer(String name, String contact_name, String contact_email, String contact_phone) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO customers (name, contact_name, contact_email, contact_phone) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, contact_name);
        preparedStatement.setString(3, contact_email);
        preparedStatement.setString(4, contact_phone);
        preparedStatement.execute();
    }

    public void editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "UPDATE customers SET name=?, contact_name=?, contact_email=?, contact_phone=? WHERE customer_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, contact_name);
        preparedStatement.setString(3, contact_email);
        preparedStatement.setString(4, contact_phone);
        preparedStatement.setInt(5, customer_id);
        preparedStatement.executeUpdate();
    }

    public int findCustomerIdFromName(String name) throws SQLException {
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT customer_id from customers where name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, '"' + name + '"');
        ResultSet resultSet = preparedStatement.executeQuery();
        String result = "" + resultSet.getObject(1);
        id = Integer.parseInt(result);
        return id;
    }

    public void deleteCustomer(int id) throws SQLException {
        Connection connection = connector.getConnection();
        String statement = "DELETE FROM customers WHERE customer_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
