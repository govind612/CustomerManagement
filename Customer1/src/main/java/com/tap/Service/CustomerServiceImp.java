package com.tap.Service;

import java.util.List;

import com.tap.DAO.CustomerDAO;
import com.tap.DAO.CustomerDAOImp;
import com.tap.model.Customer;

public class CustomerServiceImp  implements CustomerService{
//    private CustomerDAO customerDAO = new CustomerDAOImp();
		private CustomerDAO customerDAO;
		
		public CustomerServiceImp() {
			// TODO Auto-generated constructor stub 
			
			this.customerDAO=new CustomerDAOImp();
		}
		
    @Override
    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public List<Customer> getCustomers(int page, int size, String sortField, String sortDirection) {
        return customerDAO.getCustomers(page, size, sortField, sortDirection);
    }

    @Override
    public Customer getCustomerById(String uuid) {
        return customerDAO.getCustomerById(uuid);
    }

    @Override
    public void deleteCustomer(String uuid) {
        customerDAO.deleteCustomer(uuid);
    }

	@Override
	public List<Customer> getAllCustomers() {
		
		return customerDAO.getAllCustomers();
	}


}
