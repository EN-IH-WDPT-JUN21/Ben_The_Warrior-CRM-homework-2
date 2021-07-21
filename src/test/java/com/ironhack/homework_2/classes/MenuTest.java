package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.utils.JsonDatabaseUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    @DisplayName("Valid commands identified")
    void isValidCommand_ValidCommand_True() {
        assertTrue(Menu.isValidCommand("new lead"));
        assertTrue(Menu.isValidCommand("show leads"));
        assertTrue(Menu.isValidCommand("lookup lead 10"));
        assertTrue(Menu.isValidCommand("convert 20"));
        assertTrue(Menu.isValidCommand("close_won 30"));
        assertTrue(Menu.isValidCommand("close_lost 1"));
        assertTrue(Menu.isValidCommand("help"));
    }

    @Test
    @DisplayName("Invalid commands identified")
    void isValidCommand_InvalidCommand_False(){
        assertFalse(Menu.isValidCommand("new lead blah"));
        assertFalse(Menu.isValidCommand(""));
        assertFalse(Menu.isValidCommand("convert x"));
        assertFalse(Menu.isValidCommand("close won 1"));
        assertFalse(Menu.isValidCommand("show"));
    }


    @Test
    void computeCommand() {
    }
}