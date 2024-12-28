package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    // Find product by name
    @Query("SELECT p FROM Product p WHERE p.pname = :name")
    List<Product> findByProductName(String name);

    // Find products having greater price
    @Query("SELECT p FROM Product p WHERE p.pprice > :price")
    List<Product> findProductsWithPriceGreaterThan(Double price);

    // Find products by customer ID
    @Query("SELECT p FROM Product p WHERE p.customer.id = :customerId")
    List<Product> findByCustomerId(Integer customerId);

    // Find most expensive product
    @Query("SELECT p FROM Product p WHERE p.pprice = (SELECT MAX(p2.pprice) FROM Product p2)")
    Optional<Product> findMostExpensiveProduct();

    // Find count of products for a particular customer
    @Query("SELECT COUNT(p) FROM Product p WHERE p.customer.id = :customerId")
    Long countProductsByCustomer(Integer customerId);
}
