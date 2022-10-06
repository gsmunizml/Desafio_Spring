package com.group99.desafio_spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseRequestItem {
    private int productId;
    private int quantity;
}
