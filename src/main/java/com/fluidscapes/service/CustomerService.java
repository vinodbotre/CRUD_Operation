package com.fluidscapes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fluidscapes.dao.CustomerDAO;
import com.fluidscapes.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDao;  
	
	@Transactional
	public List<Customer> getAllCustomer() {  
		List<Customer> list=customerDao.getAllCustomeries();  
		return list; 
		
	}

	@Transactional
	public Customer getCustomer(int id) {  
		Customer customer=customerDao.getCustomer(id); 
		return customer; 
	}

	@Transactional
	public void addCustomer(Customer customer) { 
		customerDao.addCustomer(customer);
	}

	@Transactional
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	@Transactional
	public void deleteCustomer(int id) {  
		customerDao.deleteCustomer(id); 
	}
}
