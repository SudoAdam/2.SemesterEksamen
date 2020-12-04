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
    public CustomerData() {
        customerMapper = new CustomerMapper();
        connector = new Connector();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public ArrayList<Customer> getCustomers() {
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM customers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Customer> customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add((Customer) customerMapper.create(resultSet));
            }
            return customers;
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public Customer getCustomer(int id){
        Connection connection = connector.getConnection();
        String statement = "SELECT * FROM customers WHERE customer_id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Customer) customerMapper.create(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean createCustomer(String name, String contact_name, String contact_email, String contact_phone){
        Connection connection = connector.getConnection();
        String statement = "INSERT INTO customers name, contact_name, contact_email, contact_phone VALUES (?,?,?,?)";
        boolean success = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, contact_name);
            preparedStatement.setString(3, contact_email);
            preparedStatement.setString(4, contact_phone);
            preparedStatement.execute();
            success = true;
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return success;
    }

    public boolean editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone){
        Connection connection = connector.getConnection();
        String statement = "UPDATE customers SET name=?, contact_name=?, contact_email=?, contact_phone=? WHERE customer_id=?";
        boolean success = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, contact_name);
            preparedStatement.setString(3, contact_email);
            preparedStatement.setString(4, contact_phone);
            preparedStatement.setInt(5, customer_id);
            preparedStatement.executeUpdate();
            success = true;
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return success;
    }

    public  int findCustomerIdFromName (String name) {
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT customer_id from customers where name like ?";
        boolean success = false;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, '"' + name + '"');
            ResultSet resultSet = preparedStatement.executeQuery();
            String result = "" + resultSet.getObject(1);
            id = Integer.parseInt(result);
            success = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return id;

    }
}
