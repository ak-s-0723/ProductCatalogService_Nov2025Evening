package org.example.productcatalogservice_nov2025evening.controllers;

import org.example.productcatalogservice_nov2025evening.dtos.CategoryDto;
import org.example.productcatalogservice_nov2025evening.dtos.ProductDto;
import org.example.productcatalogservice_nov2025evening.exceptions.ProductNotFoundException;
import org.example.productcatalogservice_nov2025evening.models.Category;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    //@Qualifier("sps")
    private IProductService productService;


    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> response = new ArrayList<>();
      List<Product> products = productService.getAllProducts();
      for(Product product : products) {
          response.add(from(product));
      }
      return response;
    }

    @GetMapping("/{id}")
//    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
//        try {
//            if (id <= 0) {
//                throw new IllegalArgumentException("Please pass id > 0");
//            }
//            Product product = productService.getProductById(id);
//            if (product != null) {
//                ProductDto resp = from(product);
//                return new ResponseEntity<>(resp,HttpStatus.OK);
//            } else {
//                throw new NullPointerException("Not a valid response");
//            }
//        } catch (Exception exception) {
//            return new ResponseEntity<>((ProductDto) null, HttpStatusCode.valueOf(400));
//        }
//    }
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        if (id == 0) {
                 throw new IllegalArgumentException("Please pass id > 0");
        } else if(id < 0) {
                throw new IllegalArgumentException("Invalid Id");
        }

        Product product = productService.getProductById(id);
        if (product != null) {
            ProductDto resp = from(product);
            return new ResponseEntity<>(resp,HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product with requested id not found");
        }
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = from(productDto);
        Product response = productService.createProduct(product);
        return from(response);
    }


    @PutMapping("/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productService.replaceProduct(id, from(productDto));
        if(product != null) {
            return from(product);
        } else {
            throw new ProductNotFoundException("Product with requested id not found");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }
}
