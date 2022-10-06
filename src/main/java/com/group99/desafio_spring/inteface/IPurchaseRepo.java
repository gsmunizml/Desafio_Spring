package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.model.PurchaseTicket;

import java.util.List;

public interface IPurchaseRepo {
    List<PurchaseTicket> getAll();
    PurchaseTicket purchaseRequest(PurchaseTicket ticket);
}
