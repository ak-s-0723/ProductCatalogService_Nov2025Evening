package org.example.productcatalogservice_nov2025evening.services;

import org.example.productcatalogservice_nov2025evening.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_nov2025evening.models.Category;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class, id);

        //ToDo : DO VALIDATON HERE BY ANURAG KHANNA

        return from(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
