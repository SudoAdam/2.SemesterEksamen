/**
 * @Author Rasmus Berg and Patrick Vincent Højstrøm
 */


package com.example.demo.Mapper;

import com.example.demo.Domain.Customer;
import com.example.demo.Domain.DomainFacade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper extends AbstractMapper {
    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public Customer mapping(ResultSet resultSet) throws SQLException {
        int customer_id = resultSet.getInt("customer_id");
        String name = resultSet.getString("name");
        String contact_name = resultSet.getString("contact_name");
        String contact_email = resultSet.getString("contact_email");
        String contact_phone = resultSet.getString("contact_phone");
        return new Customer(customer_id, name, contact_name, contact_email, contact_phone);
    }
}