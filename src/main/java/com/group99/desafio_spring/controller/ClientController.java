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

    /**
     * Método responsável por criar um cliente
     * @param client - Cliente - {"nome","data-de-nascimento", "email", "endereço"}
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addClient(@RequestBody Client client) {
        service.addClient(client);
    }

    /**
     * Método responsável por retornar uma lista de clientes
     * @return List<Client>
     */
    @GetMapping()
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(service.getAllClient(), HttpStatus.OK);
    }

    /**
     * Método responsável por filtrar um cliente de acordo com seu estado
     * @param state - Estado(ex : "SP")
     * @return List<Client>
     */
    @GetMapping(params={"state"})
    public ResponseEntity<List<Client>> getClientsByState(@RequestParam String state) {
        return new ResponseEntity<>( service.getClientsByState(state), HttpStatus.OK);
    }
}
