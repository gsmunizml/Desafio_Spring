package com.group99.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private int id;
    private String name;
    private String category;
    private String brand;
    private Double price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;
}
