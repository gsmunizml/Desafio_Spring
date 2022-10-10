package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.model.ShoppingCart;
import com.group99.desafio_spring.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService service;

    /**
     * método responsável por listar todos os carrinhos
     * @return List<ShoppingCar>
     */
    @GetMapping()
    public ResponseEntity<List<ShoppingCart>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /**
     * método responsável por buscar carrinho por ID
     * @param id - id do carrinho - ex.: 1
     * @return shoppingCar
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    /**
     * método responsável por criar um carrinho
     * @param shoppingCart via body
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addShoppingCartController(@RequestBody ShoppingCart shoppingCart) {
        service.addShoppingCart(shoppingCart);
    }

    /**
     * método responsável por adicionar pedido no carrinho
     * @param id - id do carrinho - ex.: 1
     * @param purchaseTicketList via body
     */
    @PutMapping("/{id}")
    public void addPurchaseController(@PathVariable int id, @RequestBody List<PurchaseTicket> purchaseTicketList) {
        service.addPurchaseTicket(id, purchaseTicketList);
    }
}
