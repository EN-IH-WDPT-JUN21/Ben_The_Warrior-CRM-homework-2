package com.ironhack.homework_2.utils;

import com.google.gson.Gson;
import com.ironhack.homework_2.classes.*;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.io.*;
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
        Integer id=lead.getId();
        leadHash.putIfAbsent(id, lead);
    }
    //second version with creating new lead
    public void addLead(String name, String phoneNumber, String email, String companyName){
        Lead newLead= new Lead(name, phoneNumber, email, companyName);
        Integer id = setIdForNewLead(leadHash)+1;
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
    public Lead lookupLeadId(Integer id){
        if(!hasLead(id)){
            throw new IllegalArgumentException("There is no Lead with id " + id);
        }else{
            return leadHash.get(id);
        }
    }

    public Opportunity lookupOpportunityId(int id) {
        if(!hasOpportunity(id)){
            throw new IllegalArgumentException("There is no Opportunity with id " + id);
        }else{
            return opportunityHash.get(id);
        }
    }

    public Contact lookupContactId(int id) {
        if(!hasContact(id)){
            throw new IllegalArgumentException("There is no Contact with id " + id);
        }else{
            return contactHash.get(id);
        }
    }

    public Account lookupAccountId(int id) {
        if(!hasAccount(id)){
            throw new IllegalArgumentException("There is no Account with id " + id);
        }else{
            return accountHash.get(id);
        }
    }

    // ==================== Adds new Contact to HashMap for Contacts====================
    //first version from already created contact
    public void addContact(Contact contact){
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
        newContact.setId(id);

        contactHash.putIfAbsent(id, newContact);
        if(contactHash.get(id).getName().equals(leadToConvert.getName())){
        removeLead(id);}
    }
    // ==================== Adds new Opportunity to HashMap for Opportunities====================
    //count of elements in HashMap for Opportunities (plus checks if this number is not used)
    public Integer setIdForNewOpportunity (Map<Integer, Opportunity> opportunityHash){
        Integer currentOpportunitySize=opportunityHash.size();
        for (Integer idOpportunity : opportunityHash.keySet()) {
            if(idOpportunity>currentOpportunitySize){
                currentOpportunitySize=idOpportunity;
            }
        }
        return currentOpportunitySize;

    }
    //first version from already created opportunity
    public void addOpportunity(Opportunity opportunity){
        Integer id=opportunity.getId();
        opportunityHash.putIfAbsent(id, opportunity);
    }
    //second version with creating new opportunity
    public Opportunity addOpportunity(Product product, int quantity, Contact decisionMaker){
        Opportunity newOpportunity= new Opportunity( product, quantity, decisionMaker, Status.OPEN);
        Integer id = setIdForNewOpportunity(opportunityHash)+1;
        newOpportunity.setId(id);
        opportunityHash.putIfAbsent(id, newOpportunity);
        return newOpportunity;
    }
    // ==================== Adds new Account to HashMap for Accounts====================
    //count of elements in HashMap for Accounts (plus checks if this number is not used)
    public Integer setIdForNewAccount (Map<Integer, Account> accountHash){
        Integer currentAccountSize=accountHash.size();
        for (Integer idAccount : accountHash.keySet()) {
            if(idAccount>currentAccountSize){
                currentAccountSize=idAccount;
            }
        }
        return currentAccountSize;

    }
    //first version from already created account
    public void addAccount(Account account){
        Integer id=account.getId();
        accountHash.put(id, account);
    }
    //second version with creating new account
    public void addAccount(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity){
        Account newAccount= new Account(industry, employeeCount, city, country, contact, opportunity);
        Integer id = setIdForNewAccount(accountHash)+1;
        newAccount.setId(id);
        accountHash.putIfAbsent(id, newAccount);
    }

    // ==================== Converts Lead -> calls: addOpportunity, addAccount, addContact, removeLead====================
    public void convertLead(Integer id, Product product, int quantity, Industry industry, int employeeCount, String city, String country){
        addContact(id);
        Contact decisionMaker=contactHash.get(id);
        Opportunity newOpportunity=addOpportunity(product, quantity, decisionMaker);
        addAccount(industry, employeeCount, city, country, decisionMaker, newOpportunity);
//        removeLead(id);
    }

    public boolean hasLead(int id){
        if(leadHash.get(id) == null){
            return false;
        }else{
            return true;
        }
    }

    public boolean hasContact(int id){
        if(contactHash.get(id) == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean hasAccount(int id){
        if(accountHash.get(id) == null){
            return false;
        }else{
            return true;
        }
    }
    public boolean hasOpportunity(int id){
        if(opportunityHash.get(id) == null){
            return false;
        }else{
            return true;
        }
    }

    // ==================== Save Methods for Leads, Contacts, Opportunities and Accounts class into a Json files ====================
    // saveLead method will add lead info to Lead Json file

    public void saveLeadInJson(Lead lead) throws IOException {
        File file = new File("leads.json");
        FileWriter writer = new FileWriter("leads.json", true);
        String jsonLead = new Gson().toJson(lead);
        if(isInJson(lead,file)){
            System.out.println("This lead is already saved in the Json file");
            writer.close();
        }
        else{
            writer.append(new Gson().toJson(lead));
            writer.append("\n");
            writer.close();
            System.out.println("Lead has been saved correctly");
        }
    }

    // this method return a Lead from a Json file
    public Lead loadLeadFromJson(Integer id){
        Gson gson = new Gson();
        Path path = Paths.get("leads.json");
        List<Lead> leads = new ArrayList<>();
        Lead selectedLead = null;

        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            String leadString = reader.readLine();
            while (leadString != null){
                Lead lead = gson.fromJson(leadString, Lead.class);
                leads.add(lead);
                leadString = reader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for (Lead l: leads) {
            if(id == l.getId()){
                selectedLead = l;
            }
            else{
                System.out.println("This Id does not exist in the leads record");
            }
        }
        return selectedLead;
    }

    // saveContact method will add a Contact info to contacts Json file
    public void saveContactInJason(Contact contact) throws IOException {
        File file = new File("contacts.json");
        FileWriter writer = new FileWriter("contacts.json", true);
        String jsonContact = new Gson().toJson(contact);
        if(isInJson(contact,file)){
            System.out.println("This contact is already saved in the Json file");
            writer.close();
        }
        else{
            writer.append(new Gson().toJson(contact));
            writer.append("\n");
            writer.close();
            System.out.println("Contact has been saved correctly");
        }
    }

    // this method return a Contact from a Json file
    public Contact loadContactFromJson(Integer id){
        Gson gson = new Gson();
        Path path = Paths.get("contacts.json");
        List<Contact> contacts = new ArrayList<>();
        Contact selectedContact = null;

        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            String contactString = reader.readLine();
            while (contactString != null){
                Contact contact = gson.fromJson(contactString, Contact.class);
                contacts.add(contact);
                contactString = reader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for (Contact c: contacts) {
            if(id == c.getId()){
                selectedContact = c;
            }
            else{
                System.out.println("This Id does not exist in the contacts record");
            }
        }
        return selectedContact;
    }

    // saveOpportunity method will add an Opportunity object to opportunities Json file
    public void saveOpportunityInJson(Opportunity opportunity) throws IOException {
        File file = new File("opportunities.json");
        FileWriter writer = new FileWriter("opportunities.json", true);
        String jsonOpportunity = new Gson().toJson(opportunity);
        if(isInJson(opportunity,file)){
            System.out.println("This opportunity is already saved in the Json file");
            writer.close();
        }
        else{
            writer.append(new Gson().toJson(opportunity));
            writer.append("\n");
            writer.close();
            System.out.println("Opportunity has been saved correctly");
        }
    }

    // this method return an Opportunity from a Json file
    public Opportunity loadOpportunityFromJson(Integer id){
        Gson gson = new Gson();
        Path path = Paths.get("opportunities.json");
        List<Opportunity> opportunities = new ArrayList<>();
        Opportunity selectedOpportunity = null;

        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            String opportunityString = reader.readLine();
            while (opportunityString != null){
                Opportunity opportunity = gson.fromJson(opportunityString, Opportunity.class);
                opportunities.add(opportunity);
                opportunityString = reader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for (Opportunity o: opportunities) {
            if(id == o.getId()){
                selectedOpportunity = o;
            }
            else{
                System.out.println("This Id does not exist in the opportunities record");
            }
        }
        return selectedOpportunity;
    }

    // saveAccount method will add an Account object to accounts Json file
    public void saveAccountInJson(Account account) throws IOException {
        File file = new File("accounts.json");
        FileWriter writer = new FileWriter("accounts.json", true);
        String jsonAccount = new Gson().toJson(account);
        if(isInJson(account,file)){
            System.out.println("This Account is already saved in the Json file");
            writer.close();
        }
        else{
            writer.append(new Gson().toJson(account));
            writer.append("\n");
            writer.close();
            System.out.println("Account has been saved correctly");
        }
    }

    // this method return an Account from a Json file
    public Account loadAccountFromJson(Integer id){
        Gson gson = new Gson();
        Path path = Paths.get("accounts.json");
        List<Account> accounts = new ArrayList<>();
        Account selectedAccount = null;

        try(BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
            String accountString = reader.readLine();
            while (accountString != null){
                Account account = gson.fromJson(accountString, Account.class);
                accounts.add(account);
                accountString = reader.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for (Account a: accounts) {
            if(id == a.getId()){
                selectedAccount = a;
            }
            else{
                System.out.println("This Id does not exist in the accounts record");
            }
        }
        return selectedAccount;
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
