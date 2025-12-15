package org.example.productcatalogservice_nov2025evening.controllers;

import org.example.productcatalogservice_nov2025evening.dtos.ProductDto;
import org.example.productcatalogservice_nov2025evening.exceptions.ProductNotFoundException;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    //private Product product;

//    @BeforeEach
//    public void initialize() {
//        product = new Product();
//        product.setId(1L);
//        product.setName("Iphone");
//        product.setPrice(100000D);
//    }

    @Test
    public void TestGetProductById_WithValidId_ReturnProductSuccessfully()
    {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        product.setPrice(100000D);
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =
                productController.getProductById(id);

        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(id, productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone", productDtoResponseEntity.getBody().getName());
        assertEquals(100000D, productDtoResponseEntity.getBody().getPrice());
        assertEquals(HttpStatus.OK, productDtoResponseEntity.getStatusCode());
        assertNull(productDtoResponseEntity.getBody().getCategory());
        verify(productService,times(1)).getProductById(id);
    }

    @Test
    public void Test_GetProductById_WithNegativeId_ResultsInIllegalArgumentException()
    {
        //Arrange
        Long id = -1L;

        //Act and Assert

        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> productController.getProductById(id));

        assertEquals("Invalid Id", exception.getMessage());
        verify(productService,times(0)).getProductById(id);
    }

    @Test
    public void Test_GetProductById_WhereProductServiceThrowsRuntimeException_ResultsInRuntimeException() {
        //Arrange
        Long id = 100L;
        when(productService.getProductById(id)).thenThrow(RuntimeException.class);

        //Act & Assert
        assertThrows(RuntimeException.class,
                () -> productController.getProductById(id));
    }
}