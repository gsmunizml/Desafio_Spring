package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.dto.ProductDTO;
import com.group99.desafio_spring.exceptions.IdAlreadyRegisteredException;
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
}
