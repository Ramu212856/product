package com.example.demo.servcie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo prepo;
	public Product CreateProduct(Product p){
		Product product=prepo.save(p);
		return product;
	}
	
public List<Product> FindAllProducts(){
	List<Product> products=prepo.findAll();
	return products;
}
public Product FindByIdProduct(Integer id) {
	Product p=prepo.findById(id).get();
	return p;
	
}
public Product UpdateById(Integer id,Product UpdatedProduct ) {
	Product existingProduct=prepo.findById(id).orElse(null);
    if (existingProduct != null) {
        // Update the existing product's fields with the new values from updatedProduct
        existingProduct.setPname(UpdatedProduct.getPname()); // Update name
        existingProduct.setPprice(UpdatedProduct.getPprice()); // Assuming you also have price to update
         // Update description or any other fields
        
        // Save the updated product back to the repository
        return prepo.save(existingProduct); // Save and return the updated product
    } else {
        // Handle the case where the product with the given id doesn't exist
        // For example, you can throw an exception or return null
        return null; // Or throw new RuntimeException("Product not found");
    }

}
public boolean DeleteProductById(Integer id) {
	if(prepo.existsById(id)) {
		prepo.deleteById(id);
		return true;
	}
	else {
		return false;
	}
}
}
