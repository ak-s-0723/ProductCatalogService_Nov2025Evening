package org.example.productcatalogservice_nov2025evening.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_nov2025evening.models.Category;

@Setter
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private CategoryDto category;
}
