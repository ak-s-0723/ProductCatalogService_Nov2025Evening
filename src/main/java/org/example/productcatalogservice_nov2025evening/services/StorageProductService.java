package org.example.productcatalogservice_nov2025evening.services;

import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.models.State;
import org.example.productcatalogservice_nov2025evening.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }

       return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            input.setId(id);
            return productRepo.save(input);
        }

        return null;
    }

    @Override
    public void deleteProduct(Long id) {
//        Optional<Product> optionalProduct = productRepo.findById(id);
//        if(optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//            if(product.getState().equals(State.ACTIVE)) {
//                product.setState(State.DELETED);
//                productRepo.save(product);
//            } else {
                productRepo.deleteById(id);
//            }
//        }
    }


}
