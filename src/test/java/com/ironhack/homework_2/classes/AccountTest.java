package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    Account a;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testToString() {
        Contact c = new Contact("John Smith", "2460247246", "johnthewarrior@fighters.com", "The smiths");
        Opportunity o = new Opportunity(Product.HYBRID, 30000, c, Status.OPEN);
        a = new Account(Industry.ECOMMERCE, 100, "Madrid", "Spain", c, o);
        assertEquals("Id: null, Industry: ECOMMERCE, Number of Employees: 100, City: Madrid, Country: Spain, Number of Contacts: 1, Number of Opportunities: 1", a.toString());
    }
}