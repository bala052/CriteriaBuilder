package com.pirai.CriteriaBuilderr.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto
{
    private String name;
    private Double price;

    public ProductDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
