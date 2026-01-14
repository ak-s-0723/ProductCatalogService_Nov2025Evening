package org.example.productcatalogservice_nov2025evening.stubs;

import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@Service
//@Primary
public class ProductServiceStub implements IProductService {
    Map<Long,Product> productMap = new HashMap<>();

    @Override
    public Product getProductById(Long id) {
       // return productMap.get(id);
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productMap.values();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
