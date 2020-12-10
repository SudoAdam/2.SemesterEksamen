/**
 * @Author Rasmus Berg
 */

package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Domain.Customer;
import com.example.demo.Exceptions.ExecuteDeniedException;
import com.example.demo.Exceptions.QueryDeniedException;

import java.util.ArrayList;

public class CustomerService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public CustomerService(CustomerData customerData) {
        this.customerData = customerData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void createCustomer(String name, String contact_name, String contact_email, String contact_phone) throws ExecuteDeniedException {
        customerData.createCustomer(name, contact_name, contact_email, contact_phone);
    }

    public void editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone) throws QueryDeniedException {
        customerData.editCustomer(customer_id, name, contact_name, contact_email, contact_phone);
    }

    public ArrayList<Customer> getCustomers() throws QueryDeniedException {
        ArrayList<Customer> customerlist = customerData.getCustomers();
        return customerlist;
    }

    public Customer getCustomer(int id) throws QueryDeniedException {
        return customerData.getCustomer(id);
    }

    public void deleteCustomer(int id) throws ExecuteDeniedException {
        customerData.deleteCustomer(id);
    }
}
