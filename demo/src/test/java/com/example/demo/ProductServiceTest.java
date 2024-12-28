package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.servcie.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest  // Load the full application context
public class ProductServiceTest {

    @Autowired
    private ProductService productService;  // Autowire the service layer

    @Autowired
    private ProductRepo productRepo;  // Autowire the repository layer

    private Product product;

    @BeforeEach
    public void setUp() {
        // Create a test product object before each test
        product = new Product();
        product.setPname("Laptop");
        product.setPprice(1000.0);
    }

    @Test
    public void testCreateProduct() {
        // Act
        Product createdProduct = productService.createProduct(product);

        // Assert
        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getPname());
        assertEquals(1000.0, createdProduct.getPprice());
    }

    @Test
    public void testFindAllProducts() {
        // Arrange
        productService.createProduct(product);  // Ensure the product is saved before fetching all products

        // Act
        List<Product> products = productService.findAllProducts();

        // Assert
        assertNotNull(products);
        assertTrue(products.size() > 0);
    }

    @Test
    public void testFindByIdProduct() {
        // Arrange
        Product savedProduct = productService.createProduct(product);

        // Act
        Product foundProduct = productService.findByIdProduct(savedProduct.getId());

        // Assert
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getId(), foundProduct.getId());
        assertEquals("Laptop", foundProduct.getPname());
        assertEquals(1000.0, foundProduct.getPprice());
    }

    @Test
    public void testFindByIdProduct_NotFound() {
        // Act
        Product foundProduct = productService.findByIdProduct(999);  // A non-existing ID

        // Assert
        assertNull(foundProduct);  // Expecting null because product with ID 999 doesn't exist
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product savedProduct = productService.createProduct(product);

        // Prepare the updated product object
        Product updatedProduct = new Product();
        updatedProduct.setPname("Smartphone");
        updatedProduct.setPprice(800.0);

        // Act
        Product updated = productService.updateById(savedProduct.getId(), updatedProduct);

        // Assert
        assertNotNull(updated);
        assertEquals("Smartphone", updated.getPname());
        assertEquals(800.0, updated.getPprice());
    }

    @Test
    public void testUpdateProduct_NotFound() {
        // Prepare the updated product object
        Product updatedProduct = new Product();
        updatedProduct.setPname("Smartphone");
        updatedProduct.setPprice(800.0);

        // Act
        Product updated = productService.updateById(999, updatedProduct);  // Non-existing product ID

        // Assert
        assertNull(updated);  // Expecting null because product with ID 999 doesn't exist
    }

    @Test
    public void testDeleteProductById() {
        // Arrange
        Product savedProduct = productService.createProduct(product);

        // Act
        boolean result = productService.deleteProductById(savedProduct.getId());

        // Assert
        assertTrue(result);
        assertFalse(productRepo.existsById(savedProduct.getId()));  // Ensure the product is deleted from the database
    }

    @Test
    public void testDeleteProductById_NotFound() {
        // Act
        boolean result = productService.deleteProductById(999);  // Non-existing product ID

        // Assert
        assertFalse(result);  // The product doesn't exist, so return value should be false
    }
}
