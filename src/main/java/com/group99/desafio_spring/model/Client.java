package com.group99.desafio_spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Client {
    private int id;
    private String name;
    private Date birthDate;
    private String email;

    private Address address;
    private static int instanceCount = 1;

    public Client(String name, Date birthDate, String email, Address address) {
        this.id = Client.instanceCount ;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        Client.instanceCount += 1;
    }
}