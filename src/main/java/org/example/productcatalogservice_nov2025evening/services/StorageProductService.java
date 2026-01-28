package org.example.productcatalogservice_nov2025evening.services;

import org.example.productcatalogservice_nov2025evening.dtos.UserDto;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.models.State;
import org.example.productcatalogservice_nov2025evening.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("sps")
//@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public Product getProductBasedOnUserScope(Long productId, Long userId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);

        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            UserDto userDto = restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class,userId).getBody();
            if(userDto !=null) {
                System.out.println(userDto.getEmail());
                return product;
            }

            return null;
        }

        return null;
    }


}
