// Java Program to illustrate Client
//Repository File

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.Email = :Email")
    Client findByEmail(@Param("Email") String Email);
}


/*
implements DemoRepository<Client> {

    // Using an in-memory Map
    // to store the object data
    private Map<Long, Client> repository;

    public ClientRepository() {
        this.repository = new HashMap<>();
    }

    // Implementation for save method
    @Override
    public void save(Client Client
) {
        repository.put(Client.getId(), Client);
    }

    // Implementation for findClient
    //ById method
    @Override
    public ClientfindClientById(Long id) {
        return repository.get(id);
    }
        */