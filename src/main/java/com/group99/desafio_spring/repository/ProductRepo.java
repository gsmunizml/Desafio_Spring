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
import java.util.Optional;

@Repository
public class ProductRepo {
    private final String productsPathFile = "src/main/resources/products.json";
    private TicketIdGenerator ticketIdGenerator;
    ObjectMapper mapper = new ObjectMapper();

    public ProductRepo() {
        ticketIdGenerator = TicketIdGenerator.getInstance();
    }

    /**
     * Método responsável por retornar uma lista de produtos
     * @return List<Product>
     */
    public List<Product> getAll(){
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(productsPathFile), Product[].class));
        } catch (Exception ex) {
            throw new ReadFileException(ex.getMessage());
        }

        return products;
    }

    /**
     * Método responsável por buscar um produto conforme o "id"
     * @param id - identificador do produto
     * @return Optional<Product>
     */
    public Optional<Product> getProductById(int id) {
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(productsPathFile), Product[].class));
        } catch (Exception ex) {

        }

        for(Product p: products) {
            if(Integer.compare(p.getProductId(), id) == 0)
                return Optional.of(p);
        }

        return Optional.empty();
    }

    /**
     * Método responsável por adicionar uma lista de produtos
     * @param productList - Produtos([{"productId","name","category","brand":,"price","quantity","freeShipping","prestige"}])
     */
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
