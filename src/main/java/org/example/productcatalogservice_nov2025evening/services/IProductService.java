package org.example.productcatalogservice_nov2025evening.services;

import org.example.productcatalogservice_nov2025evening.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long id,Product input);
}
