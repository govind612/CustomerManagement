package com.tap.DAO;
import java.util.List;

import com.tap.model.Customer;

public interface CustomerDAO {
    void createCustomer(Customer customer);
    void updateCustomer(Customer customer);
    List<Customer> getCustomers(int page, int size, String sortField, String sortDirection);
    Customer getCustomerById(String uuid);
    void deleteCustomer(String uuid);
    int getNoOfRecords();
    public List<Customer> getAllCustomers();
    
}