/**
@Author Rasmus Berg
 */


package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Domain.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public CustomerService(CustomerData customerData){
        this.customerData = customerData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void createCustomer(String name, String contact_name, String contact_email, String contact_phone) throws SQLException {
        customerData.createCustomer(name, contact_name, contact_email, contact_phone);
    }

    public void editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone) throws SQLException{
        customerData.editCustomer(customer_id, name, contact_name, contact_email, contact_phone);
    }

    public ArrayList<Customer> getCustomers() throws SQLException{
        ArrayList<Customer> customerlist = customerData.getCustomers();
        return customerlist;
    }

    public Customer getCustomer(int id) throws SQLException{
      return customerData.getCustomer(id);
    }

    public void deleteCustomer(int id) throws SQLException{
        customerData.deleteCustomer(id);
    }
}
