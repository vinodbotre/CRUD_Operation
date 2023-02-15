package com.fluidscapes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fluidscapes.model.Customer;
import com.fluidscapes.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCustomer(Model model) {
		List<Customer> listOfCustomeries = customerService.getAllCustomer();
		model.addAttribute("customer", new Customer()); 
		model.addAttribute("listOfCustomer", listOfCustomeries);
		return "customerDetails"; 
	}

	
	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Customer getCustomerById(@PathVariable int id) {  
		Customer customer = customerService.getCustomer(id); 
		return customer;  
		
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCustomer(@ModelAttribute("customer") Customer customer) {
		if (customer.getId() == 0) {  
			customerService.addCustomer(customer); 
		} else {
			customerService.updateCustomer(customer); 
		}

		return "redirect:/getAllCustomer"; 
	}

	@RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateCustomer(@PathVariable("id") int id, Model model) {
		model.addAttribute("customer", this.customerService.getCustomer(id)); 
		model.addAttribute("listOfCustomer", this.customerService.getAllCustomer());
		return "customerDetails";
	}

	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCustomer(@PathVariable("id") int id) { //2
		customerService.deleteCustomer(id); 
		return "redirect:/getAllCustomer";

	}
}
