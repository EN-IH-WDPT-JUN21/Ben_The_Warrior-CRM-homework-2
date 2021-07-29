package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    @DisplayName("Valid commands identified")
    void isValidCommand_ValidCommand_True() {
        assertTrue(Utils.isValidCommand("new lead"));
        assertTrue(Utils.isValidCommand("show leads"));
        assertTrue(Utils.isValidCommand("lookup lead 10"));
        assertTrue(Utils.isValidCommand("convert 20"));
        assertTrue(Utils.isValidCommand("close_won 30"));
        assertTrue(Utils.isValidCommand("close_lost 1"));
        assertTrue(Utils.isValidCommand("help"));
    }

    @Test
    @DisplayName("Invalid commands identified")
    void isValidCommand_InvalidCommand_False(){
        assertFalse(Utils.isValidCommand("new lead blah"));
        assertFalse(Utils.isValidCommand(""));
        assertFalse(Utils.isValidCommand("convert x"));
        assertFalse(Utils.isValidCommand("close won 1"));
        assertFalse(Utils.isValidCommand("show"));
    }
}