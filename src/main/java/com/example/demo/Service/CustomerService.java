/**
@Author Rasmus Berg
 */


package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Domain.Customer;

public class CustomerService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public CustomerService(){
        this.customerData = new CustomerData();
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public boolean createUser(String name, String contact_name, String contact_email, String contact_phone){
        return customerData.createCustomer(name, contact_name, contact_email, contact_phone);
    }

    public boolean editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone){
        return customerData.editCustomer(customer_id, name, contact_name, contact_email, contact_phone);
    }
}
