package com.example.demo.servcie;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;

@Service("product")
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	public Product createProduct(Product product){
		Product product1=productRepo.save(product);
		return product1;
	}
	
public List<Product> findAllProducts(){
	return productRepo.findAll();
	
}
public Product findByIdProduct(Integer id) {
	return productRepo.findById(id).get();
	
}
public Product updateById(Integer id,Product updatedProduct ) {
	Product existingProduct=productRepo.findById(id).orElse(null);
    if (existingProduct != null) {
        // Update the existing product's fields with the new values from updatedProduct
        existingProduct.setPname(updatedProduct.getPname()); // Update name
        existingProduct.setPprice(updatedProduct.getPprice()); // Assuming you also have price to update
         // Update description or any other fields
        
        // Save the updated product back to the repository
        return productRepo.save(existingProduct); // Save and return the updated product
    } else {
        // Handle the case where the product with the given id doesn't exist
        // For example, you can throw an exception or return null
        return null; // Or throw new RuntimeException("Product not found");
    }

}
public boolean deleteProductById(Integer id) {
	if(productRepo.existsById(id)) {
		productRepo.deleteById(id);
		return true;
	}
	else {
		return false;
	}
}
public List<Product> findByProductName(String name){
	return productRepo.findByProductName(name);
}
public List<Product> getProductsWithPriceGreaterThan(Double price){
	return productRepo.findProductsWithPriceGreaterThan(price);
}
public List<Product> getByCustomerId(Integer customerId){
	return productRepo.findByCustomerId(customerId);
}
public Optional<Product> getMostExpensiveProduct(){
	return productRepo.findMostExpensiveProduct();
	
}
public Long countProductsByCustomer(Integer customerId) {
	return productRepo.countProductsByCustomer(customerId);
}

}
