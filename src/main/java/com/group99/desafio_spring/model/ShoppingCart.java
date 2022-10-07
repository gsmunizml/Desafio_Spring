package com.group99.desafio_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor
public class ShoppingCart {
    private int id;
    private List<PurchaseTicket> purchaseTicketList;

    private BigDecimal totalShopingCart;

    public ShoppingCart(List<PurchaseTicket> purchaseTicketList) {
        this.purchaseTicketList = purchaseTicketList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PurchaseTicket> getPurchaseTicketList() {
        return purchaseTicketList;
    }

    public void setPurchaseTicketList(List<PurchaseTicket> purchaseTicketList) {
        this.purchaseTicketList = purchaseTicketList;
    }

    public void setTotalShopingCart(BigDecimal totalShopingCart) {
        this.totalShopingCart = totalShopingCart;
    }

    public BigDecimal getTotalShopingCart() {
        return totalShopingCart;
    }

    @Override
    public String toString() {
        return "ShoppingCart " +
                "id " + id +
                ", purchaseTicketList " + purchaseTicketList +
                ", totalShopingCart " + totalShopingCart;
    }

}
