package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FullName;
    @Column(unique = true)
    private String Email;
    private String Password;
    private String Confirm;
    private String TeleNumber;

    public Client() {
    }

    public Client(Long id, String FullName, String Email, String Password, String Confirm, String TeleNumber) {
        this.id = id;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Confirm = Confirm;
        this.TeleNumber = TeleNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String Confirm) {
        this.Confirm = Confirm;
    }

    public String getTeleNumber() {
        return TeleNumber;
    }

    public void setTeleNumber(String TeleNumber) {
        this.TeleNumber = TeleNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", FullName='" + FullName + '\''
                + ", TeleNumber='" + TeleNumber + '\''
                + ", Password='" + Password + '\''
                + ", Confirm='" + Confirm + '\''
                + ", Email='" + Email + '\''
                + '}';
    }
}
