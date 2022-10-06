package com.group99.desafio_spring.service;

import com.group99.desafio_spring.inteface.IClient;
import com.group99.desafio_spring.model.Client;
import com.group99.desafio_spring.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClient {

    @Autowired
    ClientRepo repo;

    @Override
    public void addClient(Client client) {
        repo.addClient(client);
    }
}
