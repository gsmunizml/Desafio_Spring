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

    @Override
    public void addClient(Client client) {
        repo.addClient(client);
    }

    public List<Client> getAllClient() { return repo.getAll(); }

    public List<Client> getClientsByState(String state) {
        return repo.getClientsByState(state);
    }
}
