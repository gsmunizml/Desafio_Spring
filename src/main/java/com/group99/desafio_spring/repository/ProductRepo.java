package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group99.desafio_spring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepo {
    private final String pathFile = "src/main/resources/products.json";
    ObjectMapper mapper = new ObjectMapper();

    public List<Product> getAll(){
        List<Product> products = null;

        try {
            products = Arrays.asList(mapper.readValue(new File(pathFile), Product[].class));
        } catch (Exception ex) {

        }
        return products;
    }
}
