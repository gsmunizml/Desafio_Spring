package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.model.ShoppingCart;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface IShopingService {
    public List<ShoppingCart> getAll();
    public ShoppingCart getById(int id);
    public void addShoppingCart(ShoppingCart shoppingCart);
    public void addPurchaseTicket(int id, List<PurchaseTicket> purchaseTicketList);
}
