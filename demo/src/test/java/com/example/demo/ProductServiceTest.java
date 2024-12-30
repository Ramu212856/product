package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.servcie.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1, "Laptop", 1500.0, null);
    }

    @Test
    public void testCreateProduct() {
        when(productRepo.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getPname());
        assertEquals(1500.0, createdProduct.getPprice());
    }

    @Test
    public void testFindAllProducts() {
        List<Product> products = List.of(product);
        when(productRepo.findAll()).thenReturn(products);

        List<Product> productList = productService.findAllProducts();

        assertNotNull(productList);
        assertEquals(1, productList.size());
        assertEquals("Laptop", productList.get(0).getPname());
    }

    @Test
    public void testFindByIdProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));

        Product fetchedProduct = productService.findByIdProduct(1);

        assertNotNull(fetchedProduct);
        assertEquals("Laptop", fetchedProduct.getPname());
    }

    @Test
    public void testUpdateProduct() {
        when(productRepo.findById(1)).thenReturn(Optional.of(product));
        when(productRepo.save(any(Product.class))).thenReturn(product);

        product.setPname("Updated Laptop");
        Product updatedProduct = productService.updateById(1, product);

        assertNotNull(updatedProduct);
        assertEquals("Updated Laptop", updatedProduct.getPname());
    }

    @Test
    public void testUpdateProduct_NotFound() {
        when(productRepo.findById(999)).thenReturn(Optional.empty());

        Product updatedProduct = productService.updateById(999, product);

        assertNull(updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        when(productRepo.existsById(1)).thenReturn(true);

        boolean isDeleted = productService.deleteProductById(1);

        assertTrue(isDeleted);
        verify(productRepo, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteProduct_NotFound() {
        when(productRepo.existsById(999)).thenReturn(false);

        boolean isDeleted = productService.deleteProductById(999);

        assertFalse(isDeleted);
    }

    @Test
    public void testFindByProductName() {
        List<Product> products = List.of(product);
        when(productRepo.findByProductName("Laptop")).thenReturn(products);

        List<Product> productList = productService.findByProductName("Laptop");

        assertNotNull(productList);
        assertEquals(1, productList.size());
        assertEquals("Laptop", productList.get(0).getPname());
    }

    @Test
    public void testGetProductsWithPriceGreaterThan() {
        List<Product> products = List.of(product);
        when(productRepo.findProductsWithPriceGreaterThan(1000.0)).thenReturn(products);

        List<Product> productList = productService.getProductsWithPriceGreaterThan(1000.0);

        assertNotNull(productList);
        assertEquals(1, productList.size());
        assertEquals("Laptop", productList.get(0).getPname());
    }

    @Test
    public void testGetMostExpensiveProduct() {
        when(productRepo.findMostExpensiveProduct()).thenReturn(Optional.of(product));

        Optional<Product> expensiveProduct = productService.getMostExpensiveProduct();

        assertTrue(expensiveProduct.isPresent());
        assertEquals("Laptop", expensiveProduct.get().getPname());
    }

    @Test
    public void testCountProductsByCustomer() {
        when(productRepo.countProductsByCustomer(1)).thenReturn(5L);

        Long count = productService.countProductsByCustomer(1);

        assertEquals(5L, count);
    }
}
