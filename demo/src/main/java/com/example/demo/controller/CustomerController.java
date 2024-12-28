package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.servcie.CustomerService;

@RestController
@RequestMapping("/customers") 
public class CustomerController {
	
	
	@Autowired
	 @Qualifier("customer")
	private CustomerService customerService;
	@GetMapping("/greater")
	public List<Customer> getCustomerByPurchaseAmountGreater(){
		return customerService.getCustomerByPurchaseAmountGreater();
	}
	@GetMapping("/name")
	public List<Customer> getCustomersByName(@PathVariable String name){
		return customerService.getCustomersByName(name);
	}
	@GetMapping("/count")
	public Long getCountTotalCustomers() {
		return customerService.getCountTotalCustomers();
	}
	@GetMapping("/range")
	public List<Customer> getCustomersByPurchaseRange(@PathVariable Double minPurchase,@PathVariable Double maxPurchase){
		return customerService.getCustomersByPurchaseRange(minPurchase, maxPurchase);
	}
	@GetMapping("/purchase")
public List<Customer> getCustomersByNameAndPurchaseGreaterThan(@PathVariable String name,@PathVariable Double amount){
	return customerService.getCustomersByNameAndPurchaseGreaterThan(name, amount);
}
}
