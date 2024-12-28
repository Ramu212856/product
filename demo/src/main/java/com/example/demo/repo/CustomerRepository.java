package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	//Find customers by purchase amount greater than specific purchase
	@Query("Select c from Customer c Where c.cpurchase >:amount")
	List<Customer> findCustomerByPurchaseAmountGreaterThan();
	//Find customer by matching name
	@Query("Select c from Customer c where c.cname LIKE %:name%")
	List<Customer> findCustomersByName(String name);
	
    // 3. Get total number of customers (Count query)
    @Query("SELECT COUNT(c) FROM Customer c")
    long countTotalCustomers();


	// 4. Get customers who have made purchases within a certain range
    @Query("SELECT c FROM Customer c WHERE c.cpurchase BETWEEN :minPurchase AND :maxPurchase")
    List<Customer> findCustomersByPurchaseRange(Double minPurchase, Double maxPurchase);

    // 5. Find customers with a specific name and purchase amount above a threshold
    @Query("SELECT c FROM Customer c WHERE c.cname = :name AND c.cpurchase > :amount")
    List<Customer> findCustomersByNameAndPurchaseGreaterThan(String name, Double amount);
}

	


