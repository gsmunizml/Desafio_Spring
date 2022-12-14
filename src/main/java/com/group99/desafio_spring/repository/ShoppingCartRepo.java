package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.exceptions.ReadFileException;
import com.group99.desafio_spring.exceptions.WriteFileException;
import com.group99.desafio_spring.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Repository
public class ShoppingCartRepo {
    private final String pathFile = "src/main/resources/shoppingCart.json";
    ObjectMapper mapper = new ObjectMapper();


    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCartList;
        try {
            shoppingCartList = Arrays.asList(mapper.readValue(new File(pathFile), ShoppingCart[].class));
        } catch (Exception error) {
            throw new ReadFileException(error.getMessage());
        }

        return shoppingCartList;
    }


    public ShoppingCart getById(int id) {
        List<ShoppingCart> shoppingCartList;
        try {
            shoppingCartList = Arrays.asList(mapper.readValue(new File(pathFile), ShoppingCart[].class));
            return shoppingCartList.stream()
                    .filter(s -> s.getId() == id)
                    .findFirst()
                    .get();
        } catch (Exception error) {
            throw new ReadFileException(error.getMessage());
        }
    }

    public void addShopingCart(List<ShoppingCart> shoppingCartList) {
        writeObjectMapper(shoppingCartList);
    }

    public void addPurchaseTicket(ShoppingCart shoppingCart) {
        writeObjectMapper(List.of(shoppingCart));
    }


    private void writeObjectMapper(List<ShoppingCart> shoppingCart) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(new File(pathFile), shoppingCart);
        } catch (IOException error) {
            throw new WriteFileException(error.getMessage());
        }
    }
}
