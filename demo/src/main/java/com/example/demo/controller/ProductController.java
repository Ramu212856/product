package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Product;
import com.example.demo.servcie.ProductService;

@Controller
@ResponseBody
public class ProductController {
	@Autowired
	private ProductService pservice;
	@PostMapping("/product")
	public Product CreateProduct(@RequestBody Product p) {
		//TODO: process POST request
		Product product=pservice.CreateProduct(p);
		return product;
		
		
	}
	@GetMapping("/all")
	public List<Product> FindAllProducts(){
		List<Product> products=pservice.FindAllProducts();
		return products;
		
	}
	
	@GetMapping("/id/{id}")
	public Product FindByIdProduct(@PathVariable Integer id) {
		Product p=pservice.FindByIdProduct(id);
		return p;
	}
	@PatchMapping("/update/{id}")
	public Product UpdateProduct(@PathVariable Integer id,Product p) {
		Product product=pservice.UpdateById(id, p);
		return product;
	}
	@DeleteMapping("/delete/{id}")
	public String DeleteProductById(@PathVariable Integer id) {
		Boolean isDeleted=pservice.DeleteProductById(id);
			if(isDeleted) {
			return "item got deleted";
			}
			else {
				return "item not deleted";
			}
		
	}
	

}
