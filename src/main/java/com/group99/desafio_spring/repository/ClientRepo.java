package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.model.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClientRepo {
    private final String pathFile = "src/main/resources/clients.json";
    ObjectMapper mapper = new ObjectMapper();

    public List<Client> getAll(){
        List<Client> clients = null;

        try {
            clients = Arrays.asList(mapper.readValue(new File(pathFile), Client[].class));
        } catch (Exception ex) {

        }
        return clients;
    }
    public void addClient(Client client) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        List<Client> clients = new ArrayList<>();

        clients.add(client);

        try {
            writer.writeValue(new File(pathFile), clients);
        } catch (Exception ex) {

        }
    }
}
