package com.group99.desafio_spring.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private int productId;
    private String name;
    private String category;
    private String brand;
    private Double price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;
}
