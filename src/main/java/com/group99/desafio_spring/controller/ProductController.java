package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.inteface.IProduct;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProduct service;

    @GetMapping("/articles")
    public ResponseEntity<List<Product>> getList(@RequestParam Optional<String> category,
                                                    @RequestParam Optional<Boolean> freeShipping,
                                                    @RequestParam Optional<Integer> order,
                                                    @RequestParam Optional<String> prestige){

        if(order.isEmpty() && category.isEmpty() && freeShipping.isEmpty() && prestige.isEmpty())
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

        return new ResponseEntity<>(service.getFiltered(category, freeShipping, order, prestige), HttpStatus.OK);
    }

    @PostMapping("/insert-articles-request")
    public ResponseEntity<List<ProductDTO>> addProductList(@RequestBody List<Product> products) {
        return new ResponseEntity<>(service.addProductList(products), HttpStatus.CREATED);
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<PurchaseTicket> purchaseRequest(@RequestBody List<PurchaseRequestItem> purchaseRequestItems){
        return new ResponseEntity<>(service.purchaseRequest(purchaseRequestItems), HttpStatus.CREATED);
    }
}
