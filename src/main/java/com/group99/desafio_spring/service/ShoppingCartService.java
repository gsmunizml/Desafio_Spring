package com.group99.desafio_spring.service;

import com.group99.desafio_spring.exceptions.NotFoundException;
import com.group99.desafio_spring.inteface.IShopingService;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.model.ShoppingCart;
import com.group99.desafio_spring.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements IShopingService {

    @Autowired
    private ShoppingCartRepo repo;

    @Override
    public List<ShoppingCart> getAll() {
        return repo.getAll();
    }

    @Override
    public ShoppingCart getById(int id) {
        try {
            return  repo.getById(id);
        } catch (NotFoundException error) {
            throw new NotFoundException(error.getMessage());
        }
        
    }

    @Override
    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart = calculateTotalCar(shoppingCart);
        List<ShoppingCart> shoppingCartList = new ArrayList<>(this.getAll());
        shoppingCart.setId(shoppingCartList.size() + 1);
        shoppingCartList.add(shoppingCart);
        repo.addShopingCart(shoppingCartList);
    }

    @Override
    public void addPurchaseTicket(int id, List<PurchaseTicket> purchaseTicketList) {
        ShoppingCart shoppingCart = getById(id);
        purchaseTicketList.forEach(p -> shoppingCart.getPurchaseTicketList()
                .add(shoppingCart.getPurchaseTicketList().size(), p));

        repo.addPurchaseTicket(calculateTotalCar(shoppingCart));
    }
    private ShoppingCart calculateTotalCar(ShoppingCart shoppingCart) {
        var total = BigDecimal.ZERO;

        for (PurchaseTicket p: shoppingCart.getPurchaseTicketList()) {
            total = total.add(BigDecimal.valueOf(p.getTotal()));
        }

        shoppingCart.setTotalShopingCart(total);
        return shoppingCart;

    }

}
