package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.inteface.IClient;
import com.group99.desafio_spring.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClient service;

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client) {
        service.addClient(client);
    }
}
