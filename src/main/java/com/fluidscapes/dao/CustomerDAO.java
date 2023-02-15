package com.fluidscapes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fluidscapes.model.Customer;
@Repository
public class CustomerDAO {

	@Autowired  
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	
	public List<Customer> getAllCustomeries() {
		Session session = this.sessionFactory.getCurrentSession();
	
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList; //return the list to getAllCustomeries()-
	}

	
	public Customer getCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, new Integer(id)); 
		return customer; 
	}

	public Customer addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer); 
		return customer; 
	}

	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer); 
	}

	public void deleteCustomer(int id) { 
		Session session = this.sessionFactory.getCurrentSession();
		Customer p = (Customer) session.load(Customer.class, new Integer(id)); 
		if (null != p) {
			session.delete(p); 
		}
	}	
}
