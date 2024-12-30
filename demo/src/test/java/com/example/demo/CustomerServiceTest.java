package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.servcie.CustomerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1, "John Doe", 200.0, null);
    }

    @Test
    public void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer);
        
        assertNotNull(createdCustomer);
        assertEquals("John Doe", createdCustomer.getCname());
        assertEquals(200.0, createdCustomer.getCpurchase());
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> customerList = customerService.getAllCustomers();

        assertNotNull(customerList);
        assertEquals(1, customerList.size());
        assertEquals("John Doe", customerList.get(0).getCname());
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Optional<Customer> fetchedCustomer = customerService.getCustomersById(1);

        assertTrue(fetchedCustomer.isPresent());
        assertEquals("John Doe", fetchedCustomer.get().getCname());
    }

    @Test
    public void testGetCustomerById_NotFound() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        Optional<Customer> fetchedCustomer = customerService.getCustomersById(999);

        assertFalse(fetchedCustomer.isPresent());
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customer.setCname("John Doe Updated");
        Customer updatedCustomer = customerService.updateCustomer(1, customer);

        assertNotNull(updatedCustomer);
        assertEquals("John Doe Updated", updatedCustomer.getCname());
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        Customer updatedCustomer = customerService.updateCustomer(999, customer);

        assertNull(updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        boolean isDeleted = customerService.deleteCustomer(1);

        assertTrue(isDeleted);
        verify(customerRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCustomer_NotFound() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        boolean isDeleted = customerService.deleteCustomer(999);

        assertFalse(isDeleted);
    }
}
