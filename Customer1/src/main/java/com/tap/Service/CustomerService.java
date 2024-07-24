package com.tap.Service;

import java.util.List;

import com.tap.model.Customer;

public interface CustomerService {


	    void createCustomer(Customer customer);
	    void updateCustomer(Customer customer);
	    List<Customer> getCustomers(int page, int size, String sortField, String sortDirection);
	    Customer getCustomerById(String uuid);
	    void deleteCustomer(String uuid);
	    public List<Customer> getAllCustomers();



}
