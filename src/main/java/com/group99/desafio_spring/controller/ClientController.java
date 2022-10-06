package com.group99.desafio_spring.controller;

import com.group99.desafio_spring.inteface.IClient;
import com.group99.desafio_spring.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private IClient service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client) {
        service.addClient(client);
    }

    @GetMapping()
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(service.getAllClient(), HttpStatus.OK);
    }



    @GetMapping(params={"state"})
    public ResponseEntity<List<Client>> getClientsByState(@RequestParam String state) {
        return new ResponseEntity<>( service.getClientsByState(state), HttpStatus.OK);
    }
}
