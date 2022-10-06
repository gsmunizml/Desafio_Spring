package com.group99.desafio_spring.service;

import com.group99.desafio_spring.inteface.IClient;
import com.group99.desafio_spring.model.Client;
import com.group99.desafio_spring.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClient {

    @Autowired
    ClientRepo repo;

    /**
     * Método responsável por adicionar um cliente
     * @param client - Cliente
     */
    @Override
    public void addClient(Client client) {
        repo.addClient(client);
    }

    /**
     * Método responsável por retornar uma lista de clientes
     * @return List<Client>
     */
    public List<Client> getAllClient() { return repo.getAll(); }

    /**
     * Método responsável por retornar uma lista de clientes filtrado pelo estado
     * @param state - Estado que o cliente mora
     * @return List<Client>
     */
    public List<Client> getClientsByState(String state) {
        return repo.getClientsByState(state);
    }
}
