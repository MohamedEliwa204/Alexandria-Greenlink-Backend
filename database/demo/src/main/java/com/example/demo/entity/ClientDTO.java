package com.example.demo.entity;


public class ClientDTO {
    private String FullName;
    private String Email;
    private String TeleNumber;
    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getTeleNumber() {
        return TeleNumber;
    }
    public ClientDTO(String fullName, String email, String teleNumber) {
        FullName = fullName;
        Email = email;
        TeleNumber = teleNumber;
    }
    public void setTeleNumber(String teleNumber) {
        TeleNumber = teleNumber;
    }
}
