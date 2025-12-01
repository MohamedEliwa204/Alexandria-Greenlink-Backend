package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Client;
import com.example.demo.entity.Ticket;
import com.example.demo.repository.ClientService;
import com.example.demo.repository.TicketService;

@RestController
@RequestMapping("/Clients")
public class Controller {
  
    @Autowired
    private ClientService ClientService;
    @Autowired
    private TicketService TicketService;
    
    @GetMapping("")
    public List<Client> getAllClients() {
        return ClientService.getAllClients();
    }

    @GetMapping("/Tickets")
    public List<Ticket> getAllTickets(@PathVariable String Email) {
        return TicketService.getAllTickets(Email);
    }
    
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return ClientService.getClientById(id);
    }
    
    @PostMapping("")
    public Client createClient(@RequestBody Client Client) {
        return ClientService.createClient(Client);
    }

    @PostMapping("/CreateTicket/{Email}")
    public Ticket createTicket(@PathVariable String Email, @RequestBody Ticket Ticket) {
        return TicketService.createTicket(Ticket, Email);
    }
    
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client Client) {
        return ClientService.updateClient(id, Client);
    }
    
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        ClientService.deleteClient(id);
    }
}
