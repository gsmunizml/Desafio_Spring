package com.group99.desafio_spring.service;

import com.group99.desafio_spring.exceptions.NotFoundException;
import com.group99.desafio_spring.inteface.IPurchase;
import com.group99.desafio_spring.inteface.IPurchaseRepo;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements IPurchase {

    @Autowired
    private IPurchaseRepo purchaseRepo;
    @Autowired
    private ProductRepo productRepo;

    @Override
    public PurchaseTicket purchaseRequest(List<PurchaseRequestItem> purchaseRequestItems){
        PurchaseTicket ticket = new PurchaseTicket(this.getProductByItem(purchaseRequestItems));
        ticket.calculateTotal(purchaseRequestItems);

        return  purchaseRepo.purchaseRequest(ticket);
    }

    private List<Product> getProductByItem(List<PurchaseRequestItem> purchaseRequestItems){
        List<Product> products = new ArrayList<>();

        for (PurchaseRequestItem purchaseItem : purchaseRequestItems) {
            Optional<Product> product = productRepo.getProductById(purchaseItem.getProductId());

            if(product.isEmpty())
                throw new NotFoundException("Produto do Id: " + purchaseItem.getProductId() + " não encontrado");

            products.add(product.get());
        }

        return products;
    }
}
