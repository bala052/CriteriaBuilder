package com.pirai.CriteriaBuilderr.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Product")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String category;
private double price;
//
//    public Product(String name, double price) {
//        this.name = name;
//        this.price = price;
//    }
}
