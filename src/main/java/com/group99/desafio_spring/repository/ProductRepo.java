package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.model.PurchaseRequestItem;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.util.TicketIdGenerator;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ProductRepo {
    private final String productsPathFile = "src/main/resources/products.json";
    private final String ticketPathFile = "src/main/resources/tickets.json";
    private TicketIdGenerator ticketIdGenerator;
    ObjectMapper mapper = new ObjectMapper();

    public ProductRepo() {
        ticketIdGenerator = TicketIdGenerator.getInstance();
    }

    public List<Product> getAll(){
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(productsPathFile), Product[].class));
        } catch (Exception ex) {

        }
        return products;
    }

    public void addProductList(List<Product> productList) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> products = new ArrayList<>(this.getAll());

        products.addAll(productList);

        try {
            writer.writeValue(new File(productsPathFile), products);
        } catch (Exception ex) {

        }
    }

    public List<PurchaseTicket> getAllTickets(){
        List<PurchaseTicket> tickets = null;

        try {
            tickets = Arrays.asList(mapper.readValue(new File(ticketPathFile), PurchaseTicket[].class));
        } catch (Exception ex) {

        }
        return tickets;
    }

    public PurchaseTicket purchaseRequest(List<PurchaseRequestItem> purchaseRequestItems) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> products = this.getProductById(purchaseRequestItems);
        List<PurchaseTicket> tickets = new ArrayList<>(this.getAllTickets());

        PurchaseTicket ticket = new PurchaseTicket(ticketIdGenerator.getNext(), products);
        ticket.calculateTotal(purchaseRequestItems);

        tickets.add(ticket);
        try {
            writer.writeValue(new File(ticketPathFile), tickets);

            return ticket;
        } catch (Exception ex) { }

        return null;
    }

    private List<Product> getProductById(List<PurchaseRequestItem> purchaseRequestItems){
        List<Product> products = new ArrayList<>();

        List<Integer> idList = purchaseRequestItems
                .stream()
                .map(PurchaseRequestItem::getProductId)
                .collect(Collectors.toList());

       return this.getAll().stream()
                .filter(p -> idList.contains(p.getProductId()))
                .distinct()
                .collect(Collectors.toList());
    }
}
