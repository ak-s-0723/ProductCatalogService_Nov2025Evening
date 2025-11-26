package org.example.productcatalogservice_nov2025evening.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;

    //Business specific fields
    private Boolean isPrime;
    private Long commission;
}
