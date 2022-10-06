package com.group99.desafio_spring.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Client {
    private int id;
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    private String email;

    private Address address;
    private static int instanceCount = 1;

    public Client() {
    }

    public Client(String name, LocalDate birthDate, String email, Address address) {
        this.id = Client.instanceCount ;
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        Client.instanceCount += 1;
    }
}
