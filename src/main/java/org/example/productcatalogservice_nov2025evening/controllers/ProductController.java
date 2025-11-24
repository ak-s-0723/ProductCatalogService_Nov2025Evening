package org.example.productcatalogservice_nov2025evening.controllers;

import org.example.productcatalogservice_nov2025evening.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product product1 = new Product();
        product1.setName("Iphone");
        product1.setId(1L);
        product1.setPrice(5500D);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        Product product1 = new Product();
        product1.setName("Iphone");
        product1.setId(productId);
        product1.setPrice(5500D);
        return product1;
    }
}
