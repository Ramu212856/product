package com.example.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product_Description")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Using AUTO strategy for key generation
	@Column(name="Product_ID")
	private Integer id;
	@Column(name="Product_Name")
	private String pname;
	@Column(name="Product_Price")
	private Double pprice;
	@ManyToOne  //Many products can be associated with one customer
	@JoinColumn(name="Customer_Id") //Foreign Key colun inthe product table
	private Customer customer;
	

}
