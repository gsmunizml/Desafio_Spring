package com.group99.desafio_spring.inteface;

import com.group99.desafio_spring.model.Client;

import java.util.List;

public interface IClient {
    void addClient(Client client);

    List<Client> getAllClient();

}
