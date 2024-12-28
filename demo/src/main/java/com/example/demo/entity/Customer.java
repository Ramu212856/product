package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Customer_Identification")
public class Customer {
	@Id
	@Column(name="Customer_Id")
	private Integer cid;
	@Column(name="Customer_name")
	private String cname;
	@Column(name="Customer_purchase")
	private Double cpurchase;
	@OneToMany(mappedBy="customer")
	private List<Product> product;
	
	
	
	

}
