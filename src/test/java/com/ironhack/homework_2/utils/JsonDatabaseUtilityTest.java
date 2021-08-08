package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JsonDatabaseUtilityTest {
    private JsonDatabaseUtility jsonDatabaseUtility;
    private JsonDatabaseUtility initialDatabase;

    @BeforeEach
    void setUp() throws IOException {
        jsonDatabaseUtility = new JsonDatabaseUtility("dummy");
        initialDatabase = new JsonDatabaseUtility("dummy");
        initialDatabase.load();
    }

    @AfterEach
    void tearDown() throws IOException {
        initialDatabase.save();
    }

    @Test
    void getLeadHashTest() {
    }

    @Test
    void setLeadHashTest() {
    }

    @Test
    void getContactHashTest() {
    }

    @Test
    void setContactHashTest() {
    }

    @Test
    void getOpportunityHashTest() {
    }

    @Test
    void setOpportunityHashTest() {
    }

    @Test
    void getAccountHashTest() {
    }

    @Test
    void setAccountHashTest() {
    }

    @Test
    void setIdForNewLeadTest() {
        int oldId = jsonDatabaseUtility.getLeadId();
        int newIdSet = jsonDatabaseUtility.setIdForNewLead();
        int currentId = jsonDatabaseUtility.getLeadId();
        assertEquals(oldId + 1, newIdSet);
        assertEquals(newIdSet, currentId);
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        assertEquals(currentId + 1, jsonDatabaseUtility.getLeadId());
    }

    @Test
    void addLeadTest() {
        int leadHashSizeBefore = jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        int leadHashSizeAfter = jsonDatabaseUtility.getLeadHash().size();
        assertEquals(1, leadHashSizeAfter - leadHashSizeBefore);

    }

    @Test
    void removeLeadTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Before");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore = jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.removeLead(2);
        int leadHashSizeAfter = jsonDatabaseUtility.getLeadHash().size();
        System.out.println("After");
        System.out.println(jsonDatabaseUtility.getLeadHash());
        assertEquals(-1, leadHashSizeAfter - leadHashSizeBefore);
    }

    @Test
    void showLeadsTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");

    }

    @Test
    void lookupLeadIdTest() {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.lookupLeadId(2);
    }

    @Test
    void addContactTest() {
        int contactHashSizeBefore = jsonDatabaseUtility.getContactHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads before: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore = jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.addContact(2);
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeAfter = jsonDatabaseUtility.getLeadHash().size();
        int contactHashSizeAfter = jsonDatabaseUtility.getContactHash().size();
        assertEquals(1, contactHashSizeAfter - contactHashSizeBefore);
        assertEquals(-1, leadHashSizeAfter - leadHashSizeBefore);
    }

    @Test
    void addOpportunityTest() {
        int opportunityHashSizeBefore = jsonDatabaseUtility.getOpportunityHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1 = jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        assertFalse(jsonDatabaseUtility.getOpportunityHash().isEmpty());
        int opportunityHashSizeAfter = jsonDatabaseUtility.getOpportunityHash().size();
        assertEquals(1, opportunityHashSizeAfter - opportunityHashSizeBefore);
// second opportunity
        int opportunityHashSizeBefore1 = jsonDatabaseUtility.getOpportunityHash().size();
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        jsonDatabaseUtility.addContact(2);
        Contact contact2 = jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 3, contact2);
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        int opportunityHashSizeAfter1 = jsonDatabaseUtility.getOpportunityHash().size();
        assertEquals(1, opportunityHashSizeAfter1 - opportunityHashSizeBefore1);
    }

    @Test
    void addAccountTest() {
        int accountHashSizeBefore = jsonDatabaseUtility.getAccountHash().size();
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(1);
        Contact contact1 = jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.HYBRID, 12, contact1);
        Opportunity opportunity1 = jsonDatabaseUtility.getOpportunityHash().get(1);
        jsonDatabaseUtility.addAccount(Industry.MEDICAL, 12, "Warsaw", "Poland", contact1, opportunity1);
        System.out.println(jsonDatabaseUtility.getAccountHash());
        assertFalse(jsonDatabaseUtility.getAccountHash().isEmpty());
        int accountHashSizeAfter = jsonDatabaseUtility.getAccountHash().size();
        assertEquals(1, accountHashSizeAfter - accountHashSizeBefore);
    }


    @Test
    void convertLeadTest() {
        int contactHashSizeBefore = jsonDatabaseUtility.getContactHash().size();
        int opportunityHashSizeBefore = jsonDatabaseUtility.getOpportunityHash().size();
        int accountHashSizeBefore = jsonDatabaseUtility.getAccountHash().size();
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        System.out.println("Leads before: " + jsonDatabaseUtility.getLeadHash());
        int leadHashSizeBefore = jsonDatabaseUtility.getLeadHash().size();
        jsonDatabaseUtility.convertLead(2, Product.BOX, 10, Industry.ECOMMERCE, 8, "Warsaw", "Poland");
        System.out.println("Contacts after: " + jsonDatabaseUtility.getContactHash());
        System.out.println("Leads after: " + jsonDatabaseUtility.getLeadHash());
        System.out.println("Opportunities after: " + jsonDatabaseUtility.getOpportunityHash());
        System.out.println("Accounts after: " + jsonDatabaseUtility.getAccountHash());
        int leadHashSizeAfter = jsonDatabaseUtility.getLeadHash().size();
        int contactHashSizeAfter = jsonDatabaseUtility.getContactHash().size();
        int opportunityHashSizeAfter = jsonDatabaseUtility.getOpportunityHash().size();
        int accountHashSizeAfter = jsonDatabaseUtility.getAccountHash().size();
        assertEquals(1, contactHashSizeAfter - contactHashSizeBefore);
        assertEquals(-1, leadHashSizeAfter - leadHashSizeBefore);
        assertEquals(1, opportunityHashSizeAfter - opportunityHashSizeBefore);
        assertEquals(1, accountHashSizeAfter - accountHashSizeBefore);
    }

    @Test
    void saveAndLoadJsonDatabaseInJsonFile() throws IOException {
        jsonDatabaseUtility.addLead("John", "505-098-654", "john@gmail.com", "Xerox");
        jsonDatabaseUtility.addContact(1);
        Contact contact1 = jsonDatabaseUtility.getContactHash().get(1);
        jsonDatabaseUtility.addOpportunity(Product.BOX, 2, contact1);
        Opportunity opportunity1 = jsonDatabaseUtility.getOpportunityHash().get(1);
        jsonDatabaseUtility.addAccount(Industry.PRODUCE, 10, "Santiago", "Chile", contact1, opportunity1);
        jsonDatabaseUtility.addLead("Sara", "505-100-654", "sara@gmail.com", "Dell");
        jsonDatabaseUtility.addContact(2);
        Contact contact2 = jsonDatabaseUtility.getContactHash().get(2);
        jsonDatabaseUtility.addOpportunity(Product.FLATBED, 50, contact2);
        Opportunity opportunity2 = jsonDatabaseUtility.getOpportunityHash().get(2);
        jsonDatabaseUtility.addAccount(Industry.MEDICAL, 12, "Warsaw", "Poland", contact2, opportunity2);
        jsonDatabaseUtility.save();
        File file = new File("src/main/java/com/ironhack/homework_2/database/dummy.json");
        assertTrue(file.exists());

        JsonDatabaseUtility afterSaving = new JsonDatabaseUtility("dummy");
        afterSaving.load();
        assertTrue(jsonDatabaseUtility.equals(afterSaving));
    }
}