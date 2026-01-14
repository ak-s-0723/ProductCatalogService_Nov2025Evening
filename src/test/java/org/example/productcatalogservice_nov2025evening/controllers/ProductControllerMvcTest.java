package org.example.productcatalogservice_nov2025evening.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_nov2025evening.dtos.ProductDto;
import org.example.productcatalogservice_nov2025evening.models.Product;
import org.example.productcatalogservice_nov2025evening.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProducts_RunSuccessfully() throws Exception {
        //Arrange

        //This is done to be returned from mocked product service
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Macbook");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Ipad");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productService.getAllProducts()).thenReturn(productList);

        //This is done for asserting content/body/response
        ProductDto productDto1 = new ProductDto();
        productDto1.setId(1L);
        productDto1.setName("Macbook");

        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        productDto2.setName("Ipad");

        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto1);
        productDtoList.add(productDto2);


        String expectedResponse = objectMapper.writeValueAsString(productDtoList);

        //Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse))
                .andExpect(jsonPath("$[1].name").value("Ipad"));

    }

//    [
//      {
//        "id" : 1,
//        "name" : "Macbook"
//      },
//      {
//        "id" : 2,
//        "name" : "Ipad"
//     }
//    ]

//    a[0], a[2], a[2]
//     $[0], $[1], $[3]
//     $[0].id , $[1].name

    @Test
    public void Test_CreateProduct_WithValidBody_RunSuccessfully() throws Exception {

        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("IPencil");
        productDto.setPrice(25000D);

        Product product = new Product();
        product.setId(2L);
        product.setName("IPencil");
        product.setPrice(25000D);
        when(productService.createProduct(any(Product.class))).thenReturn(product);


        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("IPencil"));
    }
}


//{
//  "name" : "IPencil",
//  "price" : 25000,
//  "description" : null,
//  "category" : null,
//  "imageUrl" : null,
//  "id" : 2
//}