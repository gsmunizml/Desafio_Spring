package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;

import java.util.List;

public interface IPurchase {
    PurchaseTicket purchaseRequest(List<PurchaseRequestItem> purchaseRequestItems);
}
