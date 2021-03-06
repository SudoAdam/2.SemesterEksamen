/**
 * @Author Rasmus Berg
 */
package com.example.demo.Service;

import com.example.demo.Data.CustomerData;
import com.example.demo.Domain.Customer;
import com.example.demo.Exceptions.MapperExceptions.EmptyResultSetException;
import com.example.demo.Exceptions.DataExceptions.OperationDeniedException;
import com.example.demo.Exceptions.ServiceExceptions.FailedRequestException;
import com.example.demo.Exceptions.DataExceptions.QueryDeniedException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerService {
    // FIELDS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private final CustomerData customerData;

    // CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public CustomerService(CustomerData customerData) {
        this.customerData = customerData;
    }

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void createCustomer(String name, String contact_name, String contact_email, String contact_phone) throws FailedRequestException {
        try {
            customerData.createCustomer(name, contact_name, contact_email, contact_phone);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void editCustomer(int customer_id, String name, String contact_name, String contact_email, String contact_phone) throws FailedRequestException {
        try {
            customerData.editCustomer(customer_id, name, contact_name, contact_email, contact_phone);
        } catch (QueryDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public ArrayList<Customer> getCustomers() throws FailedRequestException {
        try {
            ArrayList<Customer> customerlist = customerData.getCustomers();
            return customerlist;
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public Customer getCustomer(int id) throws FailedRequestException {
        try {
            return customerData.getCustomer(id);
        } catch (QueryDeniedException | EmptyResultSetException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void deleteCustomer(int id) throws FailedRequestException {
        try {
            customerData.deleteCustomer(id);
        } catch (OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }

    public void addCustomerPicture(int customer_id, MultipartFile file) throws FailedRequestException {
        try {
            byte[] fileAsBytes = file.getBytes();
            customerData.uploadCustomerImg(customer_id, fileAsBytes);
        } catch (IOException | OperationDeniedException e) {
            throw new FailedRequestException(e.getMessage());
        }
    }
}
