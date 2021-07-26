package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.*;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.util.HashMap;
import java.util.Map;

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
    public Integer showLeads(){
        if(leadHash.size()>0){
            Printer.print("List of all Leads’ id and name");
            for (Lead lead : leadHash.values()) {
                Printer.print("id: " + lead.getId() + ", name: "+lead.getName());
            }
        }else{
            Printer.print("Warning: List of Leads is empty");
        }
        return leadHash.size();
    }
    // ====================  An individual Lead’s details by id ====================
    public Integer lookupLeadId(Integer id){
        Integer found=0;
        if(!leadHash.containsKey(id)){
            Printer.print("Warning: There is no Lead with Id: " +id);
            for (Lead lead : leadHash.values()) {
                Printer.print("id: " + lead.getId() + ", name: "+lead.getName());
            }
        }else{
            Lead selectedLead=leadHash.get(id);
            Printer.print("Details of Lead with Id: " + selectedLead.toString());
            found++;
        }
        return found;
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


    // ==================== Converts Lead -> calls: addOpportunity, addAccount, addContact, removeLead (in addContact method)====================
    public void convertLead(Integer id, Product product, int quantity, Industry industry, int employeeCount, String city, String country){
        addContact(id);
        Contact decisionMaker=contactHash.get(id);
        Opportunity newOpportunity=addOpportunity(product, quantity, decisionMaker);
        addAccount(industry, employeeCount, city, country, decisionMaker, newOpportunity);
//        removeLead(id);
    }

    // ====================  List of all Opportunities’ id and details ====================
    public Integer showOpportunities(){
        if(opportunityHash.size()>0){
            Printer.print("List of all Opportunities’ id and details:");
            for (Opportunity opportunity : opportunityHash.values()) {
                Printer.print(opportunity.toString());
            }
        }else{
            Printer.print("Warning: List of Opportunities is empty");
        }
        return opportunityHash.size();
    }
    // ====================  An individual Opportunity’s details by id ====================
    public Integer lookupOpportunityId(Integer id){
        Integer found=0;
        if(!opportunityHash.containsKey(id)){
            Printer.print("Warning: There is no Opportunity with Id: " + id);
            showOpportunities();
        }else{
            Opportunity selectedOpportunity=opportunityHash.get(id);
            Printer.print("Details of selected Opportunity: " + selectedOpportunity.toString());
            found++;
        }
        return found;
    }
    // ==================== Opportunity change status into close-lost====================
    public Integer closeLostId(Integer id){
        Integer changed=0;
        if(!opportunityHash.containsKey(id)){
            Printer.print("Warning: There is no Opportunity with Id: " + id);
            showOpportunities();
        }else{
            Opportunity selectedOpportunity=opportunityHash.get(id);
            selectedOpportunity.setStatus(Status.CLOSED_LOST);
            Printer.print("Status of Opportunity with id: " + selectedOpportunity.getId() + " was changed into close-lost");
            changed++;
        }
        return changed;
    }
    // ==================== Opportunity change status into close-won====================
    public Integer closeWonId(Integer id){
        Integer changed=0;
        if(!opportunityHash.containsKey(id)){
            Printer.print("Warning: There is no Opportunity with Id: " + id);
            showOpportunities();
        }else{
            Opportunity selectedOpportunity=opportunityHash.get(id);
            selectedOpportunity.setStatus(Status.CLOSED_WON);
            Printer.print("Status of Opportunity with id: " + selectedOpportunity.getId() + " was changed into close-won");
            changed++;
        }
        return changed;
    }
    // ====================  List of all Accounts’ id and details ====================
    public Integer showAccounts(){
        if(accountHash.size()>0){
            Printer.print("List of all Accounts’ id and details:");
            for (Account account : accountHash.values()) {
                Printer.print(account.toString());
            }
        }else{
            Printer.print("Warning: List of Accounts is empty");
        }
        return accountHash.size();
    }
    // ====================  An individual Account’s details by id ====================
    public Integer lookupAccountId(Integer id){
        Integer found=0;
        if(!accountHash.containsKey(id)){
            Printer.print("Warning: There is no Account with Id: " + id);
            showAccounts();
        }else{
            Account selectedAccount=accountHash.get(id);
            Printer.print("Details of selected Account: " + selectedAccount.toString());
            found++;
        }
        return found;
    }
    // ====================  List of all Contacts’ id and details ====================
    public Integer showContacts(){
        if(contactHash.size()>0){
            Printer.print("List of all Contacts’ id and details:");
            for (Contact contact : contactHash.values()) {
                Printer.print(contact.toString());
            }
        }else{
            Printer.print("Warning: List of Contacts is empty");
        }
        return contactHash.size();
    }
    // ====================  An individual Contact’s details by id ====================
    public Integer lookupContactId(Integer id){
        Integer found=0;
        if(!contactHash.containsKey(id)){
            Printer.print("Warning: There is no Contact with Id: " + id);
            showContacts();
        }else{
            Contact selectedContact=contactHash.get(id);
            Printer.print("Details of selected Contact: " + selectedContact.toString());
            found++;
        }
        return found;
    }
}
