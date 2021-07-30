package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Industry;

import java.util.ArrayList;

public class Account {
    private Integer id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private ArrayList<Contact> contactList;
    private ArrayList<Opportunity> opportunityList;

    // ============================== CONSTRUCTOR ==============================
    public Account(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        setId(id);
        setIndustry(industry);
        setEmployeeCount(employeeCount);
        setCity(city);
        setCountry(country);
        contactList = new ArrayList<>();
        contactList.add(contact);
        opportunityList = new ArrayList<>();
        opportunityList.add(opportunity);
    }

    // ============================== GETTERS & SETTERS ==============================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public ArrayList<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    public void setOpportunityList(ArrayList<Opportunity> opportunityList) {
        this.opportunityList = opportunityList;
    }

    // ============================== METHODS ==============================
    @Override
    public String toString() {
        return "Id: " + id + ", Industry: " + industry + ", Number of Employees: " + employeeCount + ", City: " + city +
            ", Country: " + country + ", Contacts: " + contactList.get(0).getName() + ", Opportunities: " + opportunityList.get(0).getProduct();
    }
}
