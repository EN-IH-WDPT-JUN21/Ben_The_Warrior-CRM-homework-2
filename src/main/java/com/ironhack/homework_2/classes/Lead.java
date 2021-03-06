package com.ironhack.homework_2.classes;

import java.util.Objects;

public class Lead {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    // ============================== CONSTRUCTOR ==============================
    public Lead(String name, String phoneNumber, String email, String companyName) {
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
        Lead lead = (Lead) o;
        return Objects.equals(name, lead.name) && Objects.equals(phoneNumber, lead.phoneNumber) &&
                Objects.equals(email, lead.email) && Objects.equals(companyName, lead.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, email, companyName);
    }

    public boolean hasNullValues(){
        return getName() == null || getPhoneNumber() == null || getEmail() == null || getCompanyName() == null;
    }
}
