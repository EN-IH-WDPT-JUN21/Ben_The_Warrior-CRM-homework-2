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
    private Integer leadId = 0;
    private Integer contactId = 0;
    private Integer opportunityId = 0;
    private Integer accountId = 0;
    private final Map<Integer, Lead> leadHash;
    private final Map<Integer, Contact> contactHash;
    private final Map<Integer, Opportunity> opportunityHash;
    private final Map<Integer, Account> accountHash;
    private transient final String DATABASE_DIRECTORY;

    // ========== CONSTRUCTORS ==========
    public JsonDatabaseUtility() {
        DATABASE_DIRECTORY = "src/main/java/com/ironhack/homework_2/database/database.json";
        leadHash = new HashMap<>();
        contactHash = new HashMap<>();
        opportunityHash = new HashMap<>();
        accountHash = new HashMap<>();
    }

    public JsonDatabaseUtility(String database) {
        DATABASE_DIRECTORY = "src/main/java/com/ironhack/homework_2/database/" + database + ".json";
        leadHash = new HashMap<>();
        contactHash = new HashMap<>();
        opportunityHash = new HashMap<>();
        accountHash = new HashMap<>();
        setLeadId(0);
        setContactId(0);
        setOpportunityId(0);
        setAccountId(0);
    }

    // ========== GETTERS AND SETTERS ==========
    public Map<Integer, Lead> getLeadHash() {
        return leadHash;
    }

    public void setLeadHash(Map<Integer, Lead> leadHash) {
        TreeMap<Integer, Lead> leadTreeMap = new TreeMap<>(leadHash);
        for (Map.Entry<Integer, Lead> entry: leadTreeMap.entrySet()){
            if (!this.leadHash.containsValue(entry.getValue()) && !entry.getValue().hasNullValues()){
                this.leadHash.put(entry.getKey(), entry.getValue());
                setLeadId(entry.getKey());
            }
        }
    }

    public Map<Integer, Contact> getContactHash() {
        return contactHash;
    }

    public void setContactHash(Map<Integer, Contact> contactHash) {
        TreeMap<Integer, Contact> contactTreeMap = new TreeMap<>(contactHash);
        for (Map.Entry<Integer, Contact> entry: contactTreeMap.entrySet()){
            if (!this.contactHash.containsValue(entry.getValue()) && !entry.getValue().hasNullValues()){
                this.contactHash.put(entry.getKey(), entry.getValue());
                setLeadId(entry.getKey());
                setContactId(entry.getKey());
            }
        }
    }

    public Map<Integer, Opportunity> getOpportunityHash() {
        return opportunityHash;
    }

    public void setOpportunityHash(Map<Integer, Opportunity> opportunityHash) {
        TreeMap<Integer, Opportunity> opportunityTreeMap = new TreeMap<>(opportunityHash);
        for (Map.Entry<Integer, Opportunity> entry: opportunityTreeMap.entrySet()){
            if (!this.opportunityHash.containsValue(entry.getValue()) && !entry.getValue().hasNullValues()){
                this.opportunityHash.put(entry.getKey(), entry.getValue());
                setOpportunityId(entry.getKey());
            }
        }
    }

    public Map<Integer, Account> getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(Map<Integer, Account> accountHash) {
        TreeMap<Integer, Account> accountTreeMap = new TreeMap<>(accountHash);
        for (Map.Entry<Integer, Account> entry: accountTreeMap.entrySet()){
            if (!this.accountHash.containsValue(entry.getValue()) && !entry.getValue().hasNullValues()){
                this.accountHash.put(entry.getKey(), entry.getValue());
                setAccountId(entry.getKey());
            }
        }
    }

    public String getDATABASE_DIRECTORY() {
        return DATABASE_DIRECTORY;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        if (leadId > getLeadId()){
            this.leadId = leadId;
        }
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        if (contactId > getContactId()){
            this.contactId = contactId;
        }
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        if (opportunityId > getOpportunityId()){
            this.opportunityId = opportunityId;
        }
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        if (accountId > getAccountId()){
            this.accountId = accountId;
        }
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
            throw new IOException("Database could not be loaded! New database created!");
        }catch (NumberFormatException e){
            throw new NumberFormatException("Database corrupted! New database created!");
        }

        try{
            setLeadHash(jsonDatabaseUtility.getLeadHash());
            setContactHash(jsonDatabaseUtility.getContactHash());
            setOpportunityHash(jsonDatabaseUtility.getOpportunityHash());
            setAccountHash(jsonDatabaseUtility.getAccountHash());
            setLeadId(jsonDatabaseUtility.getLeadId());
            setContactId(jsonDatabaseUtility.getContactId());
            setOpportunityId(jsonDatabaseUtility.getOpportunityId());
            setAccountId(jsonDatabaseUtility.getAccountId());
        } catch (NullPointerException e){
            throw new NullPointerException("Database file is empty. New database created!");
        }
    }

    // ==================== Adds new Lead to HashMap for Leads====================
    //Increments Lead's id counter and returns the new id
    public Integer setIdForNewLead() {
        setLeadId(getLeadId() + 1);
        return getLeadId();
    }

    //second version with creating new lead
    public void addLead(String name, String phoneNumber, String email, String companyName) {
        Lead newLead = new Lead(name, phoneNumber, email, companyName);
        if (!leadHash.containsValue(newLead)) {
            Integer id = setIdForNewLead();
            newLead.setId(id);
            leadHash.putIfAbsent(id, newLead);
        }
    }

    // ==================== Removes Lead from HashMap for Leads====================
    public void removeLead(Integer id) {
        leadHash.remove(id);
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
    //Increments Contact's id counter and returns the new id
    public Integer setIdForNewContact() {
        setContactId(getContactId() + 1);
        return getContactId();
    }

    //second version with creating new contact (from lead)
    public int addContact(Integer id) {
        Lead leadToConvert = leadHash.get(id);
        Contact newContact = new Contact(leadToConvert.getName(),
            leadToConvert.getPhoneNumber(),
            leadToConvert.getEmail(),
            leadToConvert.getCompanyName());
        removeLead(id);
        if (contactHash.containsValue(newContact)){
            return getContactKey(newContact);
        } else {
            id = setIdForNewContact();
            newContact.setId(id);
            contactHash.putIfAbsent(id, newContact);
            return id;
        }
    }

    // Get key from contactHash containing a specific Contact
    private int getContactKey(Contact newContact) {
        TreeMap<Integer, Contact> contactTreeMap = new TreeMap<>(contactHash);
        for (Map.Entry<Integer, Contact> entry: contactTreeMap.entrySet()){
            if (newContact.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return -1;
    }

    // ==================== Adds new Opportunity to HashMap for Opportunities====================
    //count of elements in HashMap for Opportunities (plus checks if this number is not used)
    public Integer setIdForNewOpportunity() {
        setOpportunityId(getOpportunityId() + 1);
        return getOpportunityId();
    }

    //second version with creating new opportunity
    public Opportunity addOpportunity(Product product, int quantity, Contact decisionMaker) {
        Opportunity newOpportunity = new Opportunity(product, quantity, decisionMaker, Status.OPEN);
        Integer id = setIdForNewOpportunity();
        newOpportunity.setId(id);
        opportunityHash.putIfAbsent(id, newOpportunity);
        return newOpportunity;
    }

    // ==================== Adds new Account to HashMap for Accounts====================
    //count of elements in HashMap for Accounts (plus checks if this number is not used)
    public Integer setIdForNewAccount() {
        setAccountId(getAccountId() + 1);
        return getAccountId();
    }

    //second version with creating new account
    public void addAccount(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity) {
        Account newAccount = new Account(industry, employeeCount, city, country, contact, opportunity);
        Integer id = setIdForNewAccount();
        newAccount.setId(id);
        accountHash.putIfAbsent(id, newAccount);
    }

    // ==================== Converts Lead -> calls: addOpportunity, addAccount, addContact, removeLead====================
    public void convertLead(Integer id, Product product, int quantity, Industry industry, int employeeCount, String city, String country) {
        id = addContact(id);
        Contact decisionMaker = contactHash.get(id);
        Opportunity newOpportunity = addOpportunity(product, quantity, decisionMaker);
        addAccount(industry, employeeCount, city, country, decisionMaker, newOpportunity);
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

    @Override
    public int hashCode() {
        return Objects.hash(opportunityId, accountId, leadHash, contactHash, opportunityHash, accountHash);
    }
}


