package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.*;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JsonDatabaseUtility {
    private Map<Integer, Lead> leadHash = new HashMap<>();
    private Map<Integer, Contact> contactHash = new HashMap<>();
    private Map<UUID, Opportunity> opportunityHash = new HashMap<>();
    private Map<UUID, Account> accountHash = new HashMap<>();

    // ========== CONSTRUCTORS ==========
    public JsonDatabaseUtility() {
    }

    public JsonDatabaseUtility(Map<Integer, Lead> leadHash, Map<Integer, Contact> contactHash,
                               Map<UUID, Opportunity> opportunityHash, Map<UUID, Account> accountHash) {
        setLeadHash(leadHash);
        setContactHash(contactHash);
        setOpportunityHash(opportunityHash);
        setAccountHash(accountHash);
    }
    // ========== GETTERS AND SETTERS ==========
    public Map<Integer, Lead> getLeadHash() {
        return leadHash;
    }

    public void setLeadHash(Map<Integer, Lead> leadHash) {
        this.leadHash = leadHash;
    }

    public Map<Integer, Contact> getContactHash() {
        return contactHash;
    }

    public void setContactHash(Map<Integer, Contact> contactHash) {
        this.contactHash = contactHash;
    }

    public Map<UUID, Opportunity> getOpportunityHash() {
        return opportunityHash;
    }

    public void setOpportunityHash(Map<UUID, Opportunity> opportunityHash) {
        this.opportunityHash = opportunityHash;
    }

    public Map<UUID, Account> getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(Map<UUID, Account> accountHash) {
        this.accountHash = accountHash;
    }

    // ==================== Adds new Lead to HashMap for Leads====================
    //count of elements in HashMap for Leads nad Contacts (as they are converted into Contacts to get new ID (plus checks if this number is not used)
    public Integer setIdForNewLead (Map<Integer, Lead> leadHash){
        Integer currentLeadSize=leadHash.size();
        Integer currentContactSize=contactHash.size();
        Integer currentSize= currentLeadSize + currentContactSize;
        for (Integer idLead : leadHash.keySet()) {
            if(idLead>currentSize){
                currentSize=idLead;
            }
        }
        for (Integer idContact : contactHash.keySet()) {
            if(idContact>currentSize){
                currentSize=idContact;
            }
        }
        return currentSize;

    }
    //first version from already created lead
    public void addLead(Lead lead){
        //error because lead has UUID not Integer
        Integer id=lead.getId();
        leadHash.putIfAbsent(id, lead);
    }
    //second version with creating new lead
    public void addLead(String name, String phoneNumber, String email, String companyName){
        Lead newLead= new Lead(name, phoneNumber, email, companyName);
        Integer id = setIdForNewLead(leadHash)+1;
        //error because lead has UUID not Integer
        newLead.setId(id);
        leadHash.putIfAbsent(id, newLead);
    }

    // ==================== Removes Lead from HashMap for Leads====================
    public void removeLead(Integer id){
        leadHash.remove(id);
    }
    // ====================  List of all Leads’ id and name ====================
    public void showLeads(){
        if(leadHash.size()>0){
            Printer.print("List of all Leads’ id and name");
            for (Lead lead : leadHash.values()) {
                Printer.print("id: " + lead.getId() + ", name: "+lead.getName());
            }
        }else{
            Printer.print("List of Leads is empty");
        }
    }
    // ====================  An individual Lead’s details by id ====================
    public void lookupLeadId(Integer id){
        if(leadHash.get(id).getName().isEmpty()){
            Printer.print("There is no Lead with this ID");
            for (Lead lead : leadHash.values()) {
                Printer.print("id: " + lead.getId() + ", name: "+lead.getName());
            }
        }else{
            Lead selectedLead=leadHash.get(id);
            Printer.print("Details of Lead with ID: " + id + "are: name:" + selectedLead.getName() +", phoneNumber:" +
                    selectedLead.getPhoneNumber() + ", e-mail: " + selectedLead.getEmail() +
                    ", company name: " + selectedLead.getCompanyName());
        }
    }
    // ==================== Adds new Contact to HashMap for Contacts====================
    //first version from already created contact
    public void addContact(Contact contact){
        //error because contact has UUID not Integer
        Integer id=contact.getId();
        contactHash.putIfAbsent(id, contact);
    }
    //second version with creating new contact (from lead)
    public void addContact(Integer id){
        Lead leadToConvert=leadHash.get(id);
        Contact newContact= new Contact(leadToConvert.getName(),
                leadToConvert.getPhoneNumber(),
                leadToConvert.getEmail(),
                leadToConvert.getCompanyName());
        //error because contact has UUID not Integer
        newContact.setId(id);
        contactHash.putIfAbsent(id, newContact);
    }
    // ==================== Adds new Opportunity to HashMap for Opportunities====================
    //first version from already created opportunity
    public void addOpportunity(Opportunity opportunity){
        UUID id=opportunity.getId();
        opportunityHash.putIfAbsent(id, opportunity);
    }
    //second version with creating new opportunity
    public Opportunity addOpportunity(Product product, int quantity, Contact decisionMaker){
        Opportunity newOpportunity= new Opportunity( product, quantity, decisionMaker, Status.OPEN);
        UUID id = newOpportunity.getId();
        opportunityHash.putIfAbsent(id, newOpportunity);
        return newOpportunity;
    }
    // ==================== Adds new Account to HashMap for Accounts====================
    //first version from already created account
    public void addAccount(Account account){
        UUID id=account.getId();
        accountHash.put(id, account);
    }
    //second version with creating new account
    public void addAccount(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity){
        Account newAccount= new Account(industry, employeeCount, city, country, contact, opportunity);
        UUID id = newAccount.getId();
        accountHash.putIfAbsent(id, newAccount);
    }


    // ==================== Converts Lead -> calls: addOpportunity, addAccount, addContact, removeLead====================
    public void convertLead(Integer id, Product product, int quantity, Industry industry, int employeeCount, String city, String country){
        addContact(id);
        Contact decisionMaker=contactHash.get(id);
        Opportunity newOpportunity=addOpportunity(product, quantity, decisionMaker);
        addAccount(industry, employeeCount, city, country, decisionMaker, newOpportunity);
        removeLead(id);
    }

}
