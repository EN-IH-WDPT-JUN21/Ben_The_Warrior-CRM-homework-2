package com.ironhack.homework_2.utils;

import com.google.gson.Gson;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JsonDatabaseUtility {
    private Map<Integer, Lead> leadHash = new HashMap<>();
    private Map<Integer, Contact> contactHash = new HashMap<>();
    private Map<Integer, Opportunity> opportunityHash = new HashMap<>();
    private Map<Integer, Account> accountHash = new HashMap<>();

    // ========== CONSTRUCTORS ==========
    public JsonDatabaseUtility() {
    }

    public JsonDatabaseUtility(Map<Integer, Lead> leadHash, Map<Integer, Contact> contactHash,
                               Map<Integer, Opportunity> opportunityHash, Map<Integer, Account> accountHash) {
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

    // ====================  An individual Lead’s details by id ====================
    public Lead lookupLeadId(Integer id) {
        if (!hasLead(id)) {
            throw new IllegalArgumentException("There is no Lead with id " + id);
        } else {
            return leadHash.get(id);
        }
    }

    public Opportunity lookupOpportunityId(int id) {
        if (!hasOpportunity(id)) {
            throw new IllegalArgumentException("There is no Opportunity with id " + id);
        } else {
            return opportunityHash.get(id);
        }
    }

    public Contact lookupContactId(int id) {
        if (!hasContact(id)) {
            throw new IllegalArgumentException("There is no Contact with id " + id);
        } else {
            return contactHash.get(id);
        }
    }

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

    public boolean hasLead(int id) {
        if (leadHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasContact(int id) {
        if (contactHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasAccount(int id) {
        if (accountHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasOpportunity(int id) {
        if (opportunityHash.get(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    // ==================== Save Methods for Leads, Contacts, Opportunities and Accounts class into a Json files ====================
    // save database in a json file
    public void save() throws IOException {
        File file = new File("DatabaseUtility.json");
        FileWriter writer = new FileWriter("DatabaseUtility.json", false);
        String jsonData = new Gson().toJson(this);
        if(isInJson(this,file)){
            System.out.println("This Database is already saved in the Json file");
            writer.close();
        }
        else{
            writer.write(jsonData);
            writer.append("\n");
            writer.close();
            System.out.println("Database has been saved correctly");
        }
    }

    //load method that gives maps from Database in json file to actual JsonDatabaseUtility class
    public void load() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonDatabaseUtility jsonDatabaseUtility1 = new JsonDatabaseUtility();


        File file = new File("DatabaseUtility.json");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()){
            String jsonString = sc.nextLine();
            jsonDatabaseUtility1 = gson.fromJson(jsonString, JsonDatabaseUtility.class);

        }
        sc.close();

        setLeadHash(jsonDatabaseUtility1.getLeadHash());
        setContactHash(jsonDatabaseUtility1.getContactHash());
        setOpportunityHash(jsonDatabaseUtility1.getOpportunityHash());
        setAccountHash(jsonDatabaseUtility1.getAccountHash());
    }

    //In order to avoid duplicate object in our Json Files we check if the object is already in the json file
    public boolean isInJson(Object obj, File file)throws FileNotFoundException {
        boolean isInFile = false;
        String objJson = new Gson().toJson(obj);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            if(objJson.equals(scanner.nextLine())){
                isInFile = true;
                break;
            }
            else{
                isInFile = false;
            }
        }

        scanner.close();
        return isInFile;
    }

}


