package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//Product product=new Product(1,"lappy",48000.0);
		//System.out.println(product);
	}
	
	}


