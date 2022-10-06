package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.exceptions.ReadFileExpection;
import com.group99.desafio_spring.model.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepo {
    private final String pathFile = "src/main/resources/clients.json";
    ObjectMapper mapper = new ObjectMapper();

    public List<Client> getAll() {
        List<Client> clientsList;
        try {
            clientsList = Arrays.asList(mapper.readValue(new File(pathFile), Client[].class));
        } catch (Exception error) {
            throw new ReadFileExpection(error.getMessage());
        }

        return clientsList;
    }
    public void addClient(Client client) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Client> clients = new ArrayList<>(this.getAll());
        clients.add(client);

        try {
            writer.writeValue(new File(pathFile), clients);
        } catch (Exception ex) {

        }
    }

    public List<Client> getClientsByState(String state) {
        List<Client> clientList = this.getAll();

        return clientList.stream()
                .filter((client) -> client.getAddress().getState().equals(state))
                .collect(Collectors.toList());
    }
}
