package com.example.demo.Data;

import com.example.demo.Domain.Customer;
import com.example.demo.Mapper.TaskMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerData {
        // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        private final CustomerMapper taskMapper;
        private final Connector connector;

        // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        public CustomerData() {
            customerMapper = new CustomerMapper();
            connector = new Connector();
        }

        // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        public Customer getCustomer(int id) {
        return null;
    }

    public  int findCustomerIdFromName (String name){
        int id = -1;
        Connection connection = connector.getConnection();
        String statement = "SELECT customer_id from customers where name like  ? ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,'"' + name + '"');
            ResultSet resultSet = preparedStatement.executeQuery();
            String result ="" + resultSet.getObject(1);
            id = Integer.parseInt(result);

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return id;

    }
}
