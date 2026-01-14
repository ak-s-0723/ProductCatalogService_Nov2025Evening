package org.example.productcatalogservice_nov2025evening.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_nov2025evening.models.Category;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

//    @Test
//    @Transactional
//    public void testJpaMethods() {
//        System.out.println(productRepo.getMeNameOfProductWhosePriceIs(60000D));
//    }

    @Test
    public void addProductsToAWSTables() {
        Product product =new Product();
        product.setName("Jeff bezoz");
        product.setId(1L);
        product.setPrice(-10D);

        Product product2 =new Product();
        product2.setName("Andy Jassy");
        product2.setId(2L);
        product2.setPrice(-20D);

        Category category = new Category();
        category.setId(10L);
        category.setName("CEOs");
        categoryRepo.save(category);

        product.setCategory(category);
        product2.setCategory(category);

        productRepo.save(product);
        productRepo.save(product2);

    }
}