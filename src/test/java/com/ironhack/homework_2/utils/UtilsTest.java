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

    @ParameterizedTest
    @ValueSource(strings = {"HYBRID", "FLATBED", "BOX"})
    @DisplayName("Valid Products identified")
    void isValidProduct_True(String product) {assertTrue(Utils.validProduct(product));}

    @ParameterizedTest
    @ValueSource(strings = {"hybrid", "HI BRITT", "FLAP JACK", "BROCK"})
    @DisplayName("Invalid Products identified")
    void isValidProduct_False(String product) {assertFalse(Utils.validProduct(product));}

    @ParameterizedTest
    @ValueSource(strings = {"PRODUCE", "ECOMMERCE", "MANUFACTURING", "MEDICAL", "OTHER"})
    @DisplayName("Valid Industry identified")
    void isValidIndustry_True(String industry) {assertTrue(Utils.validIndustry(industry));}

    @ParameterizedTest
    @ValueSource(strings = {"produce", "PRO DUCKS", "ECO MR MECES", "MAN FACT RING", "MEDICINE BALL", "OTTER"})
    @DisplayName("Invalid Industry identified")
    void isValidIndustry_False(String industry) {assertFalse(Utils.validIndustry(industry));}

    @ParameterizedTest
    @ValueSource(strings = {"Bob Kelso", "José Brasão", "John", "John Bradshaw Layfield"})
    @DisplayName("Valid Name identified")
    void isValidName_True(String names) {assertTrue(Utils.validName(names));}

    @ParameterizedTest
    @ValueSource(strings = {"", "J0sé"})
    @DisplayName("Invalid Name identified")
    void isValidName_False(String names) {assertFalse(Utils.validName(names));}

    @ParameterizedTest
    @ValueSource(strings = {"jd@sacredheart.com", "TheJanitor123@sacredheart.com"})
    @DisplayName("Valid Email identified")
    void isValidEmail_True(String emails) {assertTrue(Utils.validEmail(emails));}

    @ParameterizedTest
    @ValueSource(strings = {"myemailATnowhere", "myemail@nowhere"})
    @DisplayName("Invalid Email identified")
    void isValidEmail_False(String emails) {assertFalse(Utils.validEmail(emails));}

    @ParameterizedTest
    @ValueSource(strings = {"01256543624", "01256 543 624", "(012) 56543624", "+44 01256543624"})
    @DisplayName("Valid Phone Number identified")
    void isValidPhoneNumber_True(String phoneNumbers) {assertTrue(Utils.validPhone(phoneNumbers));}

    @ParameterizedTest
    @ValueSource(strings = {"", "123", "123hello456"})
    @DisplayName("Invalid Phone Number identified")
    void isValidPhoneNumber_False(String phoneNumbers) {assertFalse(Utils.validPhone(phoneNumbers));}

    @ParameterizedTest
    @ValueSource(strings = {"London", "Ashby-de-la-Zouch", "King's Norton", "Provence-Alpes-Côte d'Azur",
            "Sauðárkrókur", "Übach-Palenberg"})
    @DisplayName("Valid Address identified")
    void isValidString_True(String address) {assertTrue(Utils.validString(address));}

    @ParameterizedTest
    @ValueSource(strings = {"", "//", "london", "London2", "Somewhere?",
            "Anywhere-", "übach-Palenberg"})
    @DisplayName("Invalid Address identified")
    void isValidString_False(String address) {assertFalse(Utils.validString(address));}
}