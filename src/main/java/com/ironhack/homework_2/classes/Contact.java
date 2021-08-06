package com.ironhack.homework_2.classes;

import java.util.Objects;

public class Contact {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    // ============================== CONSTRUCTOR ==============================
    public Contact(String name, String phoneNumber, String email, String companyName) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    // ============================== GETTERS & SETTERS ==============================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // ============================== METHODS ==============================
    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber + ", Company: " + companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(email, contact.email) && Objects.equals(companyName, contact.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, companyName);
    }
}
