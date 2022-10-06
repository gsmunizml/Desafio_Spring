package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.model.Client;
import com.group99.desafio_spring.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepo {
    private final String pathFile = "src/main/resources/clients.json";

    ObjectMapper mapper = new ObjectMapper();

    public void addClient(Client client) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<Client> = new ArrayList<>()

        try {
            writer.writeValue(new File(pathFile), products);
        } catch (Exception ex) {

        }
    }
}
