package com.group99.desafio_spring.util;

import lombok.NoArgsConstructor;

//Singleton
@NoArgsConstructor
public class TicketIdGenerator {
    private int next;
    private final static TicketIdGenerator instancia = new TicketIdGenerator();

    public static TicketIdGenerator getInstance() {
        return instancia;
    }

    public int getNext() {
        return ++next;
    }
}
