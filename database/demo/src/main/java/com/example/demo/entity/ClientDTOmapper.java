package com.example.demo.entity;
public class ClientDTOmapper {

    public ClientDTO mapper(Client client){
        return new ClientDTO(client.getFullName(), client.getEmail(), client.getTeleNumber());
    }
} 
