package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.servcie.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	@Qualifier("product")
	private ProductService productService;
	@PostMapping("/product")
	public Product CreateProduct(@RequestBody Product product) {
		//TODO: process POST request
		Product product1=productService.createProduct(product);
		return product1;
		
		
	}
	@GetMapping("/all")
	public List<Product> findAllProducts(){
		List<Product> products=productService.findAllProducts();
		return products;
		
	}
	
	@GetMapping("/id/{id}")
	public Product findByIdProduct(@PathVariable Integer id) {
		Product p=productService.findByIdProduct(id);
		return p;
	}
	@PatchMapping("/update/{id}")
	public Product updateProduct(@PathVariable Integer id,Product p) {
		Product product=productService.updateById(id, p);
		return product;
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProductById(@PathVariable Integer id) {
		Boolean isDeleted=productService.deleteProductById(id);
			if(Boolean.TRUE.equals(isDeleted)) {
			return "item got deleted";
			}
			else {
				return "item not deleted";
			}
		
	}
	

}
