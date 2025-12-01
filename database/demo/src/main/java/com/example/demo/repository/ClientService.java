package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;

@Service
public class ClientService {

    @Autowired
    private ClientRepository ClientRepository;



    public List<Client> getAllClients() {
        return ClientRepository.findAll();
    }

    public Client getClientByEmail(String Email)
    {
        return ClientRepository.findByEmail(Email);
    }

    public Client getClientById(Long id) {
        return ClientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client Client) {
        return ClientRepository.save(Client);
    }

    public Client updateClient(Long id, Client Client) {
        Client existingClient = ClientRepository.findById(id).orElse(null);
        if (existingClient!= null && (Client.getConfirm()).equals(Client.getPassword())) {
            existingClient.setFullName(Client.getFullName());
            existingClient.setEmail(Client.getEmail());
            existingClient.setPassword(Client.getPassword());
            existingClient.setConfirm(Client.getConfirm());
            existingClient.setTeleNumber(Client.getTeleNumber());
            return ClientRepository.save(existingClient);
        } else {
            return null;
        }
    }

    public void deleteClient(Long id) {
        ClientRepository.deleteById(id);
    }

}
