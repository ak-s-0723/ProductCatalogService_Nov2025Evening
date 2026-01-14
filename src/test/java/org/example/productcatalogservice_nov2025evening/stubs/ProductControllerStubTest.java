package org.example.productcatalogservice_nov2025evening.stubs;

import org.example.productcatalogservice_nov2025evening.controllers.ProductController;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductControllerStubTest {

    @Autowired
    ProductController productController;

    @Autowired
    IProductService productService;
}
