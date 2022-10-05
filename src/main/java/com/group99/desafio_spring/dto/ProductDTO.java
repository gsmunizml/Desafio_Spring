package com.group99.desafio_spring.dto;

import com.group99.desafio_spring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private int quantity;
    public ProductDTO(Product product){
        this.id = product.getProductId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }
}
