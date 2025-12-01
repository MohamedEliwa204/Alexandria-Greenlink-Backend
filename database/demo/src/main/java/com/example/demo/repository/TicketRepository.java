// Java Program to illustrate Ticket
//Repository File

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByClient_id(Long Client_id);
}


/*
implements DemoRepository<Ticket> {

    // Using an in-memory Map
    // to store the object data
    private Map<Long, Ticket> repository;

    public TicketRepository() {
        this.repository = new HashMap<>();
    }

    // Implementation for save method
    @Override
    public void save(Ticket Ticket
) {
        repository.put(Ticket.getId(), Ticket);
    }

    // Implementation for findTicket
    //ById method
    @Override
    public TicketfindTicketById(Long id) {
        return repository.get(id);
    }
        */