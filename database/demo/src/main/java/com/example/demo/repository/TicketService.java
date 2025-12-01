package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Ticket;

@Service
public class TicketService {

    @Autowired
    private TicketRepository TicketRepository;

    @Autowired
    private ClientRepository ClientRepository;

    public List<Ticket> getAllTickets(String Email) {
        return TicketRepository.findAllByClient_id(ClientRepository.findByEmail(Email).getId());
    }

    public Ticket getTicketByEmail(String Email) {
        return TicketRepository.findById(ClientRepository.findByEmail(Email).getId()).orElse(null);
    }

    public Ticket getTicketById(Long id) {
        return TicketRepository.findById(id).orElse(null);
    }

    public Ticket createTicket(Ticket Ticket, String Email) {
        Client c = ClientRepository.findByEmail(Email);
        if (c != null) {
            Ticket.setClient_id(c.getId());
            return TicketRepository.save(Ticket);
        }
        return null;
    }

    public Ticket updateTicket(Long id, Ticket Ticket) {
        Ticket existingTicket = TicketRepository.findById(id).orElse(null);
        if (existingTicket != null) {
            existingTicket.setClient_id(Ticket.getClient_id());
            existingTicket.setPrice(Ticket.getPrice());
            existingTicket.setFrom(Ticket.getFrom());
            existingTicket.setTo(Ticket.getTo());
            existingTicket.setTransNumber(Ticket.getTransNumber());
            return TicketRepository.save(existingTicket);
        } else {
            return null;
        }
    }

    public void deleteTicket(Long id) {
        TicketRepository.deleteById(id);
    }
}
