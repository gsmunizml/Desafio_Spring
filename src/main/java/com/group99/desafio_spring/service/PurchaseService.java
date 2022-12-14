package com.group99.desafio_spring.service;

import com.group99.desafio_spring.exceptions.NoProductsException;
import com.group99.desafio_spring.exceptions.NotFoundException;
import com.group99.desafio_spring.exceptions.ReadFileException;
import com.group99.desafio_spring.inteface.IPurchase;
import com.group99.desafio_spring.inteface.IPurchaseRepo;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements IPurchase {

    @Autowired
    private IPurchaseRepo purchaseRepo;
    @Autowired
    private ProductRepo productRepo;

    /**
     * Método responsável pelo envio de um pedido de compra
     * @param purchaseRequestItems lista dos produtos pedidos, o objeto é composto pelo Id do produto e sua quantidade.
     * @return será retornado o ticket criado para a compra solicitada
     *
     */
    @Override
    public PurchaseTicket purchaseRequest(List<PurchaseRequestItem> purchaseRequestItems){
        PurchaseTicket ticket = new PurchaseTicket(this.getProductByItems(purchaseRequestItems));
        ticket.calculateTotal(purchaseRequestItems);

        return  purchaseRepo.purchaseRequest(ticket);
    }

    /**
     * Busca os produtos de acordo com o itens passados no pedido.
     * @param purchaseRequestItems lista dos produtos pedidos, o objeto é composto pelo Id do produto e sua quantidade.
     * @return retorna lista com os produtos filtrados de acordo com a lista de itens do pedido
     */
    private List<Product> getProductByItems(List<PurchaseRequestItem> purchaseRequestItems){
        List<Product> products = new ArrayList<>();

        for (PurchaseRequestItem purchaseItem : purchaseRequestItems) {
            Optional<Product> product = productRepo.getProductById(purchaseItem.getProductId());

            if(product.isEmpty())
                throw new NotFoundException("Produto do Id: " + purchaseItem.getProductId() + " não encontrado");

            product.get().setQuantity(purchaseItem.getQuantity());
            products.add(product.get());
        }

        setProductInventory(purchaseRequestItems);
        return products;
    }

    /**
     * Atualiza os itens contidos no estoque.
     * @param purchaseRequestItems lista de itens que serão comprados.
     */
    private void setProductInventory(List<PurchaseRequestItem> purchaseRequestItems) {
        List<Product> products = productRepo.getAll();

        for (PurchaseRequestItem purchaseItem : purchaseRequestItems) {
            products.forEach(product -> {
                        if (product.getProductId() == purchaseItem.getProductId()) {
                            if (product.getQuantity() < purchaseItem.getQuantity()) {
                                throw new NoProductsException("Não há produtos disponíveis no estoque.");
                            }
                            product.setQuantity(product.getQuantity() - purchaseItem.getQuantity());
                        }
                    });
        }
        productRepo.updateInventory(products);
    }
}
