package org.example.productcatalogservice_nov2025evening.controllers;

import org.example.productcatalogservice_nov2025evening.dtos.CategoryDto;
import org.example.productcatalogservice_nov2025evening.dtos.ProductDto;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;


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
    public ProductDto getProductById(@PathVariable Long id) {
       Product product = productService.getProductById(id);
       if (product != null) {
           return from(product);
       }

       return null;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    private ProductDto from (Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
