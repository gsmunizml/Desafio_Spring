package com.group99.desafio_spring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.group99.desafio_spring.exceptions.ReadFileException;
import com.group99.desafio_spring.exceptions.WriteFileException;
import com.group99.desafio_spring.inteface.IPurchaseRepo;
import com.group99.desafio_spring.model.PurchaseTicket;
import com.group99.desafio_spring.util.TicketIdGenerator;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PurchaseRepo implements IPurchaseRepo {
    private final String ticketPathFile = "src/main/resources/tickets.json";
    private TicketIdGenerator ticketIdGenerator;
    ObjectMapper mapper = new ObjectMapper();

    public PurchaseRepo(){
        ticketIdGenerator = TicketIdGenerator.getInstance();
    }

    /**
     * Método criado para buscar toda a lista de pedidos de compra
     * @return Retorna todos os tickets de pedidos de compras
     */
    @Override
    public List<PurchaseTicket> getAll() {
        List<PurchaseTicket> tickets = null;

        try {
            tickets = Arrays.asList(mapper.readValue(new File(ticketPathFile), PurchaseTicket[].class));
        } catch (Exception ex) {
            throw new ReadFileException(ex.getMessage());
        }

        return tickets;
    }

    /**
     * Método para criação do pedido de compra e atribuição do seu Id
     * @param ticket modelo do ticket do pedido de compra, já com a listagem de produtos e o total do pedido
     * @return retorna o ticket criado para o pedido de compra
     */
    public PurchaseTicket purchaseRequest(PurchaseTicket ticket) {
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<PurchaseTicket> tickets = new ArrayList<>(this.getAll());

        ticket.setId(ticketIdGenerator.getNext());
        tickets.add(ticket);

        try {
            writer.writeValue(new File(ticketPathFile), tickets);
            return ticket;
        } catch (Exception ex) {
            throw new WriteFileException(ex.getMessage());
        }
    }
}
