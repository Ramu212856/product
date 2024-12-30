package com.example.demo.servcie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;

@Service("customer")
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	public List<Customer> getCustomerByPurchaseAmountGreater(){
		return customerRepository.findCustomerByPurchaseAmountGreaterThan();
		
	}
	public List<Customer> getCustomersByName(String name){
		return customerRepository.findCustomersByName(name);
	}
	public Long getCountTotalCustomers(){
		return customerRepository.countTotalCustomers();
	}
	public List<Customer> getCustomersByPurchaseRange(Double minPurchase,Double maxPurchase){
		return customerRepository.findCustomersByPurchaseRange(minPurchase, maxPurchase);
	}
	public List<Customer> getCustomersByNameAndPurchaseGreaterThan(String name,Double amount){
		return customerRepository.findCustomersByNameAndPurchaseGreaterThan(name, amount);
	}
	public Customer createCustomer(Customer customer) {
	Customer customer1=	customerRepository.save(customer);
	return customer1;	
	}
	public List<Customer> getAllCustomers(){
		List<Customer> customer1=customerRepository.findAll();
		return customer1;
	}
	 public Optional<Customer> getCustomersById(Integer customerId) {
		 Optional<Customer> customer=customerRepository.findById(customerId);
		 return customer;
	 }
	// Update an existing customer
	    public Customer updateCustomer(Integer customerId, Customer customerDetails) {
	        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

	        if (existingCustomer.isPresent()) {
	            Customer customerToUpdate = existingCustomer.get();
	            customerToUpdate.setCname(customerDetails.getCname());
	            customerToUpdate.setCpurchase(customerDetails.getCpurchase());
	            customerToUpdate.setProduct(customerDetails.getProduct());
	            return customerRepository.save(customerToUpdate);
	        } else {
	            return null; // If customer not found
	        }
	    }

	    // Delete a customer
	    public boolean deleteCustomer(Integer customerId) {
	        Optional<Customer> existingCustomer = customerRepository.findById(customerId);

	        if (existingCustomer.isPresent()) {
	            customerRepository.deleteById(customerId);
	            return true;
	        } else {
	            return false; // Customer not found
	        }
	    }

	
	
	
	

}
