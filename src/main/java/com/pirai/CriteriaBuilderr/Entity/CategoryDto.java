package com.pirai.CriteriaBuilderr.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto
{
    private String category;
    private Double avgPrice;
    private Long count;

    public CategoryDto() {
    }

    public CategoryDto(String category, Double avgPrice, Long count) {
        this.category = category;
        this.avgPrice = avgPrice;
        this.count = count;
    }
}
