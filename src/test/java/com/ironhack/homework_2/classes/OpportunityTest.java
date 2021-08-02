package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpportunityTest {

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
        assertEquals("Id: null, Product: HYBRID, Quantity: 30000, Decision Maker: John Smith, Status: OPEN", o.toString());
    }
}