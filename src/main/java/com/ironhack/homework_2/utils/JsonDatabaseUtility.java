package com.ironhack.homework_2.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ironhack.homework_2.classes.Account;
import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JsonDatabaseUtility {
    private Integer leadId;
    private Integer opportunityId;
    private Integer accountId;
    private Map<Integer, Lead> leadHash;
    private Map<Integer, Contact> contactHash;
    private Map<Integer, Opportunity> opportunityHash;
    private Map<Integer, Account> accountHash;
    private transient final String DATABASE_DIRECTORY;

    // ========== CONSTRUCTORS ==========
    public JsonDatabaseUtility() {
        DATABASE_DIRECTORY = "src/main/java/com/ironhack/homework_2/database/database.json";
        leadHash = new HashMap<>();
        contactHash = new HashMap<>();
        opportunityHash = new HashMap<>();
        accountHash = new HashMap<>();
        setLeadId(0);
        setOpportunityId(0);
        setAccountId(0);
    }

    public JsonDatabaseUtility(String database) {
        DATABASE_DIRECTORY = "src/main/java/com/ironhack/homework_2/database/" + database + ".json";
        leadHash = new HashMap<>();
        contactHash = new HashMap<>();
        opportunityHash = new HashMap<>();
        accountHash = new HashMap<>();
        setLeadId(0);
        setOpportunityId(0);
        setAccountId(0);
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

    public Map<Integer, Opportunity> getOpportunityHash() {
        return opportunityHash;
    }

    public void setOpportunityHash(Map<Integer, Opportunity> opportunityHash) {
        this.opportunityHash = opportunityHash;
    }

    public Map<Integer, Account> getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(Map<Integer, Account> accountHash) {
        this.accountHash = accountHash;
    }

    public String getDATABASE_DIRECTORY() {
        return DATABASE_DIRECTORY;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    // ==================== Save Methods for Leads, Contacts, Opportunities and Accounts class into a Json files ====================
    // save database in a json file
    public void save() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        File file = new File(getDATABASE_DIRECTORY());
        FileWriter writer = new FileWriter(file, false);
        String jsonData = gson.toJson(this);
        writer.write(jsonData);
        writer.close();
    }

    //load method that gives maps from Database in json file to actual JsonDatabaseUtility class
    public void load() throws IOException {
        Gson gson = new Gson();
        File file = new File(getDATABASE_DIRECTORY());
        JsonDatabaseUtility jsonDatabaseUtility;
        try{
            FileReader reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            String jsonData = new String(chars);
            jsonDatabaseUtility = gson.fromJson(jsonData, JsonDatabaseUtility.class);
            reader.close();
        }catch (IOException e){
            throw new IOException("Database could not be loaded! Empty database created!");
        }

        setLeadHash(jsonDatabaseUtility.getLeadHash());
        setContactHash(jsonDatabaseUtility.getContactHash());
        setOpportunityHash(jsonDatabaseUtility.getOpportunityHash());
        setAccountHash(jsonDatabaseUtility.getAccountHash());
        setLeadId(jsonDatabaseUtility.getLeadId());
        setOpportunityId(jsonDatabaseUtility.getOpportunityId());
        setAccountId(jsonDatabaseUtility.getAccountId());
    }

    /*// Save database
    public static void saveDatabaseInJson(JsonDatabaseUtility database) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        File file = new File(DATABASE_DIRECTORY);
        FileWriter writer = new FileWriter(file, false);
        String jsonData = gson.toJson(database);
        writer.write(jsonData);
        writer.close();
    }

    // Load database
    public static JsonDatabaseUtility loadDatabaseInJson() throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File(DATABASE_DIRECTORY);
        FileReader reader = new FileReader(file);
        try{
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            String jsonData = new String(chars);
            JsonDatabaseUtility jsonDatabaseUtility = gson.fromJson(jsonData, JsonDatabaseUtility.class);
            reader.close();
            return jsonDatabaseUtility;
        }catch (Exception e){
            return new JsonDatabaseUtility();
        }
    }*/

    // ==================== Adds new Lead to HashMap for Leads====================
    //count of elements in HashMap for Leads nad Contacts (as they are converted into Contacts to get new ID (plus checks if this number is not used)
    public Integer setIdForNewLead(Map<Integer, Lead> leadHash) {
        Integer currentLeadSize = leadHash.size();
        Integer currentContactSize = contactHash.size();
        Integer currentSize = currentLeadSize + currentContactSize;
        for (Integer idLead : leadHash.keySet()) {
            if (idLead > currentSize) {
                currentSize = idLead;
            }
        }
        for (Integer idContact : contactHash.keySet()) {
            if (idContact > currentSize) {
                currentSize = idContact;
            }
        }
        return currentSize;

    }

    //first version from already created lead
    public void addLead(Lead lead) {
        Integer id = lead.getId();
        leadHash.putIfAbsent(id, lead);
    }

    //second version with creating new lead
    public void addLead(String name, String phoneNumber, String email, String companyName) {
        Lead newLead = new Lead(name, phoneNumber, email, companyName);
        Integer id = setIdForNewLead(leadHash) + 1;
        newLead.setId(id);
        leadHash.putIfAbsent(id, newLead);
    }

    // ==================== Removes Lead from HashMap for Leads====================
    public void removeLead(Integer id) {
        leadHash.remove(id);
    }

    // ====================  List of all Leads’ id and name ====================
    public void showLeads() {
        if (leadHash.size() > 0) {
            Printer.print("List of all Leads’ id and name");
            for (Lead lead : leadHash.values()) {
                Printer.print("id: " + lead.getId() + ", name: " + lead.getName());
            }
        } else {
            Printer.print("List of Leads is empty");
        }
    }
    // Search a lead from id. If it doesn't exist throw exception
    public Lead lookupLeadId(Integer id) {
        if (!hasLead(id)) {
            throw new IllegalArgumentException("There is no Lead with id " + id);
        } else {
            return leadHash.get(id);
        }
    }
    // Search a opportunity from id. If it doesn't exist throw exception
    public Opportunity lookupOpportunityId(int id) {
        if (!hasOpportunity(id)) {
            throw new IllegalArgumentException("There is no Opportunity with id " + id);
        } else {
            return opportunityHash.get(id);
        }
    }
    // Search a contact from id. If it doesn't exist throw exception
    public Contact lookupContactId(int id) {
        if (!hasContact(id)) {
            throw new IllegalArgumentException("There is no Contact with id " + id);
        } else {
            return contactHash.get(id);
        }
    }
    // Search a account from id. If it doesn't exist throw exception
    public Account lookupAccountId(int id) {
        if (!hasAccount(id)) {
            throw new IllegalArgumentException("There is no Account with id " + id);
        } else {
            return accountHash.get(id);
        }
    }

    // ==================== Adds new Contact to HashMap for Contacts====================
    //first version from already created contact
    public void addContact(Contact contact) {
        Integer id = contact.getId();
        contactHash.putIfAbsent(id, contact);
    }

    //second version with creating new contact (from lead)
    public void addContact(Integer id) {
        Lead leadToConvert = leadHash.get(id);
        Contact newContact = new Contact(leadToConvert.getName(),
            leadToConvert.getPhoneNumber(),
            leadToConvert.getEmail(),
            leadToConvert.getCompanyName());
        newContact.setId(id);

        contactHash.putIfAbsent(id, newContact);
        if (contactHash.get(id).getName().equals(leadToConvert.getName())) {
            removeLead(id);
        }
    }

    // ==================== Adds new Opportunity to HashMap for Opportunities====================
    //count of elements in HashMap for Opportunities (plus checks if this number is not used)
    public Integer setIdForNewOpportunity(Map<Integer, Opportunity> opportunityHash) {
        Integer currentOpportunitySize = opportunityHash.size();
        for (Integer idOpportunity : opportunityHash.keySet()) {
            if (idOpportunity > currentOpportunitySize) {
                currentOpportunitySize = idOpportunity;
            }
        }
        return currentOpportunitySize;

    }

    //first version from already created opportunity
    public void addOpportunity(Opportunity opportunity) {
        Integer id = opportunity.getId();
        opportunityHash.putIfAbsent(id, opportunity);
    }

    //second version with creating new opportunity
    public Opportunity addOpportunity(Product product, int quantity, Contact decisionMaker) {
        Opportunity newOpportunity = new Opportunity(product, quantity, decisionMaker, Status.OPEN);
        Integer id = setIdForNewOpportunity(opportunityHash) + 1;
        newOpportunity.setId(id);
        opportunityHash.putIfAbsent(id, newOpportunity);
        return newOpportunity;
    }

    // ==================== Adds new Account to HashMap for Accounts====================
    //count of elements in HashMap for Accounts (plus checks if this number is not used)
    public Integer setIdForNewAccount(Map<Integer, Account> accountHash) {
        Integer currentAccountSize = accountHash.size();
        for (Integer idAccount : accountHash.keySet()) {
            if (idAccount > currentAccountSize) {
                currentAccountSize = idAccount;
            }
        }
        return currentAccountSize;

    }

    //first version from already created account
    public void addAccount(Account account) {
        Integer id = account.getId();
        accountHash.put(id, account);
    }

    //second version with creating new account
    public void addAccount(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        Account newAccount = new Account(industry, employeeCount, city, country, contact, opportunity);
        Integer id = setIdForNewAccount(accountHash) + 1;
        newAccount.setId(id);
        accountHash.putIfAbsent(id, newAccount);
    }

    // ==================== Converts Lead -> calls: addOpportunity, addAccount, addContact, removeLead====================
    public void convertLead(Integer id, Product product, int quantity, Industry industry, int employeeCount, String city, String country) {
        addContact(id);
        Contact decisionMaker = contactHash.get(id);
        Opportunity newOpportunity = addOpportunity(product, quantity, decisionMaker);
        addAccount(industry, employeeCount, city, country, decisionMaker, newOpportunity);
//        removeLead(id);
    }

    // Method to check if a lead exists with a specific id
    public boolean hasLead(int id) {
        if (leadHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    // Method to check if a contact exists with a specific id
    public boolean hasContact(int id) {
        if (contactHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    // Method to check if a account exists with a specific id
    public boolean hasAccount(int id) {
        if (accountHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }
    // Method to check if a opportunity exists with a specific id
    public boolean hasOpportunity(int id) {
        if (opportunityHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonDatabaseUtility that = (JsonDatabaseUtility) o;
        return Objects.equals(opportunityId, that.opportunityId) && Objects.equals(accountId, that.accountId) && Objects.equals(leadHash, that.leadHash) && Objects.equals(contactHash, that.contactHash) && Objects.equals(opportunityHash, that.opportunityHash) && Objects.equals(accountHash, that.accountHash);
    }
}


