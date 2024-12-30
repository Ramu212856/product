package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.servcie.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    @Qualifier("customer")
    private CustomerService customerService;

    // Get customers with purchase amount greater than a certain value
    @GetMapping("/greater")
    public List<Customer> getCustomerByPurchaseAmountGreater() {
        return customerService.getCustomerByPurchaseAmountGreater();
    }

    // Get customers by name
    @GetMapping("/name")
    public List<Customer> getCustomersByName(@RequestParam String name) {
        return customerService.getCustomersByName(name);
    }

    // Get total number of customers
    @GetMapping("/count")
    public Long getCountTotalCustomers() {
        return customerService.getCountTotalCustomers();
    }

    // Get customers by purchase range
    @GetMapping("/range")
    public List<Customer> getCustomersByPurchaseRange(
            @RequestParam Double minPurchase, @RequestParam Double maxPurchase) {
        return customerService.getCustomersByPurchaseRange(minPurchase, maxPurchase);
    }

    // Get customers by name and purchase amount greater than a certain value
    @GetMapping("/purchase")
    public List<Customer> getCustomersByNameAndPurchaseGreaterThan(
            @RequestParam String name, @RequestParam Double amount) {
        return customerService.getCustomersByNameAndPurchaseGreaterThan(name, amount);
    }

    // Get a customer by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        Optional<Customer> customer = customerService.getCustomersById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new customer
    @PostMapping("customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    // Update an existing customer by ID
    // Update an existing customer by ID
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Integer customerId, @RequestBody Customer customerDetails) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Customer not found
        }
    }

    // Delete a customer by ID
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer customerId) {
        boolean isDeleted = customerService.deleteCustomer(customerId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Customer not found
        }
    }
}