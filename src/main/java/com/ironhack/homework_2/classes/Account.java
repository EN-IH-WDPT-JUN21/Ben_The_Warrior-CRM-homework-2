package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Industry;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

    public ArrayList<Opportunity> getOpportunityList() {
        return opportunityList;
    }

    // ============================== METHODS ==============================
    @Override
    public String toString() {
        return "Id: " + id + ", Industry: " + industry + ", Number of Employees: " + employeeCount + ", City: " + city +
            ", Country: " + country + ", Number of Contacts: " + contactList.size() + ", Number of Opportunities: " + opportunityList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return employeeCount == account.employeeCount && industry == account.industry &&
                Objects.equals(city, account.city) && Objects.equals(country, account.country) &&
                Objects.equals(contactList, account.contactList) && Objects.equals(opportunityList, account.opportunityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(industry, employeeCount, city, country, contactList, opportunityList);
    }

    public boolean hasNullValues(){
        if (getIndustry() == null || getCity() == null || getCountry() == null || getContactList() == null || getOpportunityList() == null){
            return true;
        }
        for (Opportunity opportunity : getOpportunityList()){
            if (opportunity.hasNullValues()){
                return true;
            }
        }
        for (Contact contact : getContactList()){
            if (contact.hasNullValues()){
                return true;
            }
        }
        return false;
    }
}
