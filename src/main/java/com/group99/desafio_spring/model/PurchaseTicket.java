package com.group99.desafio_spring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseTicket {
    private int id;
    private List<Product> articles;
    private double total;

    public PurchaseTicket(int id, List<Product> articles) {
        this.id = id;
        this.articles = articles;
    }

    public void calculateTotal(List<PurchaseRequestItem> purchaseRequestItems){
        for (PurchaseRequestItem purchaseRequestItem: purchaseRequestItems) {
            Product product = articles.stream()
                    .filter(p -> p.getProductId() == purchaseRequestItem.getProductId()).findFirst().get();

            total += product.getPrice() * purchaseRequestItem.getQuantity();
        }
    }
}
