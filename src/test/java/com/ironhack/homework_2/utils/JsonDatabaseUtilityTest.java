package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonDatabaseUtilityTest {
    private JsonDatabaseUtility jsonDatabaseUtility;

    @BeforeEach
    void setUp() {
        jsonDatabaseUtility= new JsonDatabaseUtility();

    }

    @Test
    void setIdForNewLeadTest() {
        int leadHashSize=jsonDatabaseUtility.getLeadHash().size();
        System.out.println(leadHashSize);
        int newId= jsonDatabaseUtility.setIdForNewLead(jsonDatabaseUtility.getLeadHash());
        System.out.println(newId);
        assertEquals(0, newId-leadHashSize);
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        int leadHashSize1=jsonDatabaseUtility.getLeadHash().size();
        System.out.println(leadHashSize1);
        int newId1= jsonDatabaseUtility.setIdForNewLead(jsonDatabaseUtility.getLeadHash());
        System.out.println(newId1);
        assertEquals(1, newId1);
        assertEquals(0, newId1-leadHashSize1);
    }

    @Test
    void addLeadTest() {
        int leadHashSizeBefore=jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        int leadHashSizeAfter=jsonDatabaseUtility.getLeadHash().size();
        assertEquals(1, leadHashSizeAfter-leadHashSizeBefore);

    }

    @Test
    void removeLeadTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Before");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore=jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.removeLead(2);
        int leadHashSizeAfter=jsonDatabaseUtility.getLeadHash().size();
        System.out.println("After");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        assertEquals(-1, leadHashSizeAfter-leadHashSizeBefore);
    }

    @Test
    void showLeadsTest() {
        Printer.print("Leads before: ");
        assertEquals(0,jsonDatabaseUtility.showLeads());
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        Printer.print("Leads after: ");
        assertEquals(2,jsonDatabaseUtility.showLeads());

    }

    @Test
    void lookupLeadIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        assertEquals(1, jsonDatabaseUtility.lookupLeadId(2));
        //        for not existing id warning
        assertEquals(0, jsonDatabaseUtility.lookupLeadId(3));
    }

    @Test
    void addContactTest() {
        int contactHashSizeBefore=jsonDatabaseUtility.getContactHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads before: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore=jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.addContact(2);
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeAfter=jsonDatabaseUtility.getLeadHash().size();
        int contactHashSizeAfter=jsonDatabaseUtility.getContactHash().size();
        assertEquals(1, contactHashSizeAfter-contactHashSizeBefore);
        assertEquals(-1, leadHashSizeAfter-leadHashSizeBefore);
    }

    @Test
    void addOpportunityTest() {
        int opportunityHashSizeBefore=jsonDatabaseUtility.getOpportunityHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        assertFalse(jsonDatabaseUtility.getOpportunityHash().isEmpty());
        int opportunityHashSizeAfter=jsonDatabaseUtility.getOpportunityHash().size();
        assertEquals(1, opportunityHashSizeAfter-opportunityHashSizeBefore);
// second opportunity
        int opportunityHashSizeBefore1=jsonDatabaseUtility.getOpportunityHash().size();
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        jsonDatabaseUtility.addContact(2);
        Contact contact2=jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        int opportunityHashSizeAfter1=jsonDatabaseUtility.getOpportunityHash().size();
        assertEquals(1, opportunityHashSizeAfter1-opportunityHashSizeBefore1);
    }

    @Test
    void addAccountTest() {
        int accountHashSizeBefore=jsonDatabaseUtility.getAccountHash().size();
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity1=jsonDatabaseUtility.getOpportunityHash().get(1);
        jsonDatabaseUtility.addAccount(Industry.MEDICAL, 12, "Warsaw", "Poland", contact1, opportunity1);
        System.out.println(jsonDatabaseUtility.getAccountHash());
        assertFalse(jsonDatabaseUtility.getAccountHash().isEmpty());
        int accountHashSizeAfter=jsonDatabaseUtility.getAccountHash().size();
        assertEquals(1, accountHashSizeAfter-accountHashSizeBefore);
    }


    @Test
    void convertLeadTest() {
        int contactHashSizeBefore=jsonDatabaseUtility.getContactHash().size();
        int opportunityHashSizeBefore=jsonDatabaseUtility.getOpportunityHash().size();
        int accountHashSizeBefore=jsonDatabaseUtility.getAccountHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads before: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore=jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.convertLead(2, Product.BOX, 10, Industry.ECOMMERCE, 8, "Warsaw", "Poland");
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        System.out.println("Accounts after: " + jsonDatabaseUtility.getAccountHash());
        int leadHashSizeAfter=jsonDatabaseUtility.getLeadHash().size();
        int contactHashSizeAfter=jsonDatabaseUtility.getContactHash().size();
        int opportunityHashSizeAfter=jsonDatabaseUtility.getOpportunityHash().size();
        int accountHashSizeAfter=jsonDatabaseUtility.getAccountHash().size();
        assertEquals(1, contactHashSizeAfter-contactHashSizeBefore);
        assertEquals(-1, leadHashSizeAfter-leadHashSizeBefore);
        assertEquals(1, opportunityHashSizeAfter-opportunityHashSizeBefore);
        assertEquals(1, accountHashSizeAfter-accountHashSizeBefore);
    }

    @Test
    void showOpportunitiesTest() {
        Printer.print("Opportunities before: ");
        assertEquals(0, jsonDatabaseUtility.showOpportunities());
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
// second opportunity
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Contact contact2=jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        Printer.print("Opportunities after: ");
        assertEquals(2, jsonDatabaseUtility.showOpportunities());

    }
    @Test
    void lookupOpportunityIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
// second opportunity
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Contact contact2=jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        assertEquals(1, jsonDatabaseUtility.lookupOpportunityId(2));
        //        for not existing id warning
        assertEquals(0, jsonDatabaseUtility.lookupOpportunityId(3));
    }

    @Test
    void closeLostIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity= jsonDatabaseUtility.getOpportunityHash().get(1);
        assertEquals(1, jsonDatabaseUtility.closeLostId(1));
        assertEquals(Status.CLOSED_LOST, opportunity.getStatus());
//        for not existing id warning
        assertEquals(0, jsonDatabaseUtility.closeLostId(2));
    }

    @Test
    void closeWonIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity= jsonDatabaseUtility.getOpportunityHash().get(1);
        assertEquals(1, jsonDatabaseUtility.closeWonId(1));
        assertEquals(Status.CLOSED_WON, opportunity.getStatus());
        //        for not existing id only warning
        assertEquals(0, jsonDatabaseUtility.closeWonId(2));
    }

    @Test
    void showAccountsTest() {
        Printer.print("Accounts before: ");
        assertEquals(0, jsonDatabaseUtility.showAccounts());
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity1=jsonDatabaseUtility.getOpportunityHash().get(1);
        jsonDatabaseUtility.addAccount(Industry.MEDICAL, 12, "Warsaw", "Poland", contact1, opportunity1);
// second account
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Contact contact2=jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        Opportunity opportunity2=jsonDatabaseUtility.getOpportunityHash().get(2);
        jsonDatabaseUtility.addAccount(Industry.ECOMMERCE, 32, "London", "UK", contact2, opportunity2);
        Printer.print("Accounts after: ");
        assertEquals(2, jsonDatabaseUtility.showAccounts());

    }
    @Test
    void lookupAccountIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1=jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity1=jsonDatabaseUtility.getOpportunityHash().get(1);
        jsonDatabaseUtility.addAccount(Industry.MEDICAL, 12, "Warsaw", "Poland", contact1, opportunity1);
// second account
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Contact contact2=jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        Opportunity opportunity2=jsonDatabaseUtility.getOpportunityHash().get(2);
        jsonDatabaseUtility.addAccount(Industry.ECOMMERCE, 32, "London", "UK", contact2, opportunity2);
        assertEquals(1, jsonDatabaseUtility.lookupAccountId(2));
        //        for not existing id warning
        assertEquals(0, jsonDatabaseUtility.lookupAccountId(3));
    }
    @Test
    void showContactsTest() {
        Printer.print("Contacts before: ");
        assertEquals(0, jsonDatabaseUtility.showContacts());
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
// second contact
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Printer.print("Contacts after: ");
        assertEquals(2, jsonDatabaseUtility.showContacts());

    }

    @Test
    void lookupContactIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
 // second account
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        assertEquals(1, jsonDatabaseUtility.lookupContactId(2));
        //        for not existing id warning
        assertEquals(0, jsonDatabaseUtility.lookupContactId(3));
    }
}