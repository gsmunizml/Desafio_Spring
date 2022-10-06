package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.inteface.IPurchase;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {

    @Autowired
    private IPurchase service;

    @PostMapping("/purchase-request")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PurchaseTicket> purchaseRequest(@RequestBody List<PurchaseRequestItem> purchaseRequestItems){
        return new ResponseEntity<>(service.purchaseRequest(purchaseRequestItems), HttpStatus.CREATED);
    }
}
