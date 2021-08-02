package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "8", "41", "100", "9999"})
    @DisplayName("Valid positive number identified")
    void isValidPositive_ValidPositiveNumber_True(String command) {
        assertTrue(Utils.isValidPositiveNumber(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "not a number", "one", "-1", "-26", "88888888888"})
    @DisplayName("Invalid positive number commands identified")
    void isValidPositive_InvalidPositiveNumber_False(String command) {
        assertFalse(Utils.isValidPositiveNumber(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"new lead", "show leads", "lookup lead 10", "convert 20", "close-won 30", "close-lost 1",
        "help", "exit"})
    @DisplayName("Valid commands identified")
    void isValidCommand_ValidCommand_True(String command) {
        assertTrue(Utils.isValidCommand(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"new lead blah", "", "convert x", "close won 1", "show"})
    @DisplayName("Invalid commands identified")
    void isValidCommand_InvalidCommand_False(String command) {
        assertFalse(Utils.isValidCommand(command));
    }
}