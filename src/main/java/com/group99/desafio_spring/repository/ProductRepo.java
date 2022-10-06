package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.exceptions.IdAlreadyRegisteredException;
import com.group99.desafio_spring.exceptions.ReadFileException;
import com.group99.desafio_spring.exceptions.WriteFileException;
import com.group99.desafio_spring.model.Product;
import com.group99.desafio_spring.util.TicketIdGenerator;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            throw new ReadFileException(ex.getMessage());
        }

        return products;
    }

    public void addProductList(List<Product> productList) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> savedProducts = new ArrayList<>(this.getAll());

        productList.forEach((product) -> {
            savedProducts.forEach((savedProduct) -> {
                if (savedProduct.getProductId() == product.getProductId()) {
                    throw new IdAlreadyRegisteredException("O id: " + product.getProductId() + " já está cadastrado.");
                }
            });
        });

        savedProducts.addAll(productList);

        try {
            writer.writeValue(new File(productsPathFile), savedProducts);
        } catch (Exception ex) {
            throw new WriteFileException(ex.getMessage());
        }
    }
}
