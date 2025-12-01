package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long Client_id;
    private Long Price;
    private String From ;
    private String To ;
    private LocalDate Date ;
    private Long TransNumber;

    public Ticket() {
    }

    public Ticket(Long id, Long Client_id, Long Price, String From, String To, LocalDate Date, Long TransNumber) {
        this.id = id;
        this.Client_id = Client_id;
        this.Price = Price;
        this.From = From;
        this.To = To;
        this.Date = Date;
        this.TransNumber = TransNumber;
    }

    public Long getId() {
        return id;
    }
    public Long setId(Long id) {
        return id;
    }

    public Long getClient_id() {
        return Client_id;
    }

    public void setClient_id(Long Client_id) {
        this.Client_id = Client_id;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To ) {
        this.To = To;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public Long getTransNumber() {
        return TransNumber;
    }

    public void setTransNumber(Long TransNumber) {
        this.TransNumber = TransNumber;
    }

    public Long  getPrice() {
        return Price;
    }

    public void setPrice(Long Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + "Client_id=" + Client_id
                + ", Price='" + Price + '\''
                + ", TransNumber='" + TransNumber + '\''
                + ", To='" + To + '\''
                + ", Date='" + Date + '\''
                + ", From='" + From + '\''
                + '}';
    }
}
