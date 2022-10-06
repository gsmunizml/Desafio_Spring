package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    /**
     * Método responsável por retornar uma lista de clientes
     * @return List<Client>
     */
    public List<Client> getAll() {
        mapper.findAndRegisterModules();
        List<Client> clientsList;

        try {
            clientsList = Arrays.asList(mapper.readValue(new File(pathFile), Client[].class));
        } catch (Exception error) {
            throw new ReadFileExpection(error.getMessage());
        }

        return clientsList;
    }

    /**
     * Método responsável por adicionar um cliente
     * @param client- Cliente - {"nome","data-de-nascimento", "email", "endereço"}
     */
    public void addClient(Client client) {
        mapper.findAndRegisterModules();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Client> clients = new ArrayList<>(this.getAll());

        Client newClient = new Client(client.getName(),
                client.getBirthDate(),
                client.getEmail(),
                client.getAddress());

        clients.add(newClient);

        try {
            writer.writeValue(new File(pathFile), clients);
        } catch (Exception ex) {

        }
    }

    /**
     * Método responsável por filtrar clientes conforme o estado
     * @param state - Estado(ex: "SP")
     * @return List<Client>
     */
    public List<Client> getClientsByState(String state) {
        List<Client> clientList = this.getAll();

        return clientList.stream()
                .filter((client) -> client.getAddress().getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
}
