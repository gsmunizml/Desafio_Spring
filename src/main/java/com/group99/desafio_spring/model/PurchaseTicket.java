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

    public PurchaseTicket(List<Product> articles) {
        this.articles = articles;
    }

    public void calculateTotal(List<PurchaseRequestItem> purchaseRequestItems){
        for (PurchaseRequestItem purchaseRequestItem: purchaseRequestItems) {
            Product product = articles.stream()
                    .filter(p -> p.getProductId() == purchaseRequestItem.getProductId()).findFirst().get();

            total += product.getPrice() * purchaseRequestItem.getQuantity();
        }
    }

    @Override
    public String toString() {
        return "PurchaseTicket{" +
                "id=" + id +
                ", articles=" + articles +
                ", total=" + total +
                '}';
    }
}
