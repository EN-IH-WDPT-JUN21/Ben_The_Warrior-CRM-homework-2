package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrinterTest {
    private final static String RED = "\u001B[31m";
    private final static String RESET = "\u001B[0m";
    private final static String BRIGHT_GREEN = "\u001B[92m";
    private final static String BG_RED = "\u001B[41m";

    // Setup for print testing.
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }


    // ============================== AUX METHODS TESTS ==============================
    // ==================== TEST divideText() ====================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "TestTest", " Test Test", "Test Test ", "TestTestTest", "Test Test Test",
        " Test Test t", "TestTestTestTestTestTestTest", "Test test TestTest Test Test",
        "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest Test "})
    @DisplayName("Divide Text - Smaller text")
    void divideText_textEqualOrSmallerThanEmptySpace_originalStringAndEmptyString(String s) {
        String[] dividedString = Printer.divideText(s, s.length());
        assertTrue(dividedString[0].equals(s) && dividedString[1].equals(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest", "TestTestTest", "TestTestTestTestTestTestTest",
        "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest Test "})
    @DisplayName("Divide Text - Larger first word 1")
    void divideText_firstWordLargerThanEmptySpace_firstWordSplitByEmptySpaceAndRemainingStringExists(String s) {
        String[] dividedString = Printer.divideText(s, 3);
        assertTrue(dividedString[0].equals("Tes") && dividedString[1].length() > 0);
    }

    @Test
    @DisplayName("Divide Text - Larger first word 2")
    void divideText_firstWordLargerThanEmptySpace_correctSplitText() {
        String[] dividedString = Printer.divideText("TestTestTest Test", 6);
        assertTrue(dividedString[0].equals("TestTe") && dividedString[1].equals("stTest Test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {" Test Test", "Test Test ", "Test Test Test", " Test Test t",
        "Test test TestTest Test Test"})
    @DisplayName("Divide Text - Larger text 1")
    void divideText_textLargerThanEmptySpace_wordsThatFitAndRemainingStringExists(String s) {
        String[] dividedString = Printer.divideText(s, 6);
        assertTrue(dividedString[0].equals("Test ") && dividedString[1].length() > 0);
    }

    @Test
    @DisplayName("Divide Text - Larger text 2")
    void divideText_textLargerThanEmptySpace_correctSplitText() {
        String[] dividedString = Printer.divideText("Test Test TestTest", 12);
        assertTrue(dividedString[0].equals("Test Test ") && dividedString[1].equals("TestTest"));
    }

    // ==================== TEST numberOfTextRows() ====================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "TestTest", " Test Test", "Test Test "})
    @DisplayName("Number of Rows - Smaller text")
    void numberOfTextRows_textSmallerThanEmptySpace_OneRow(String s) {
        assertEquals(1, Printer.numberOfTextRows(s, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTestTest", "Test Test Test", " Test Test t", "TestTestTestTestTestTestTest",
        "Test test TestTest Test Test"})
    @DisplayName("Number of Rows - Larger text")
    void numberOfTextRows_textLargerThanEmptySpace_moreThanOneRow(String s) {
        assertTrue(Printer.numberOfTextRows(s, 10) > 1);
    }

    @ParameterizedTest
    @CsvSource({"6,10", "10,5", "12,5", "18,4", "19,3", "24,3", "30,2", "49,1", "100,1"})
    @DisplayName("Number of Rows - Exact number of rows")
    void numberOfTextRows_fixedTextWithVariableEmptySpace_correctNumberOfRows(int inputEmptySpace, int expectedRows) {
        String s = "Test Test Test Test Test Test Test Test Test Test";
        assertEquals(expectedRows, Printer.numberOfTextRows(s, inputEmptySpace));
    }

    // ==================== TEST numberOfTextRows() with color codes ====================
    @ParameterizedTest
    @ValueSource(strings = {RED + RESET, RED + " " + RESET, BRIGHT_GREEN + "   " + RESET,
        RED + "Test" + BRIGHT_GREEN + "Test" + RESET, BG_RED + " Test Test" + RESET,
        BRIGHT_GREEN + "Test " + BG_RED + "Test "})
    @DisplayName("Number of Rows - Smaller text with color")
    void numberOfTextRows_textSmallerThanEmptySpaceWithColor_OneRow(String s) {
        assertEquals(1, Printer.numberOfTextRows(s, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {RED + "TestTestTest" + RESET, "Test " + BG_RED + "Test" + RESET + " Test",
        " Te" + BG_RED + "st Test" + RESET + " t", RED + "TestTest" + RESET + "TestTestTest" + BRIGHT_GREEN + "TestTest",
        "Test" + RED + " test Test" + BG_RED + "Test Test" + RESET + " Test"})
    @DisplayName("Number of Rows - Larger text with color")
    void numberOfTextRows_textLargerThanEmptySpaceWithColor_moreThanOneRow(String s) {
        assertTrue(Printer.numberOfTextRows(s, 10) > 1);
    }

    @ParameterizedTest
    @CsvSource({"6,10", "10,5", "12,5", "18,4", "19,3", "24,3", "30,2", "49,1", "100,1"})
    @DisplayName("Number of Rows - Exact number of rows with color")
    void numberOfTextRows_fixedTextWithVariableEmptySpaceWithColor_correctNumberOfRows(int inputEmptySpace, int expectedRows) {
        String s = "Test " + RED + "Test Test" + BRIGHT_GREEN + " Test Test " + BG_RED + "Test Test" + RESET + " Test Test Test";
        assertEquals(expectedRows, Printer.numberOfTextRows(s, inputEmptySpace));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 13, 17, 18, 20})
    @DisplayName("Number of Rows - Same rows with and without color")
    void numberOfTextRows_textWithAndWithoutColor_sameNumberOfRows(int emptySize) {
        String s = "Test TestTest Test";
        String sc = "Test " + RED + "Test" + BRIGHT_GREEN + "Test" + BG_RED + " Test" + RESET;
        assertEquals(Printer.numberOfTextRows(s, emptySize), Printer.numberOfTextRows(sc, emptySize));
    }

    // ==================== TEST getColorCodes() ====================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     ", "Test", "TestTestTestTestTestTestTest",
        "TestTest TestTestTest - TestTest: Test? `\"", "-931t3tn 9qcg7637 613",
        "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613"})
    @DisplayName("Color Code Mapping - No colors")
    void getColorCodes_noASCIIColorCode_EmptyTreeMap(String s) {
        assertTrue(Printer.getColorCodes(s).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {RED + "" + RESET, RED + " " + RESET, RED + "Test" + RESET,
        RED + "TestTestTestTestTestTestTest" + RESET, RED + "TestTest TestTestTest - TestTest: Test? `\"" + RESET,
        RED + "-931t3 tn9qcg7 637613" + RESET,
        RED + "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613" + RESET})
    @DisplayName("Color Code Mapping - Two colors")
    void getColorCodes_twoASCIIColorCode_TreeMapWithSizeTwo(String s) {
        assertEquals(2, Printer.getColorCodes(s).size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + RESET,
        "Blablaba " + RED + "TestTestTest" + RESET + " TestTestTest"})
    @DisplayName("Color Code Mapping - Two colors - Indexes")
    void getColorCodes_twoASCIIColorCodeInIndex9And26_correctColorCodeIndexes(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertEquals(9, cm.firstKey());
        assertEquals(26, cm.lastKey());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + BG_RED + "Lalallalalaala" + RESET,
        "Blablaba " + RED + "TestTestTest" + BRIGHT_GREEN + " TestTestTest" + BG_RED + "Lalalala" + RESET})
    @DisplayName("Color Code Mapping - Multiple colors")
    void getColorCodes_multipleASCIIColorCode_savesAllColorCode(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertTrue(cm.containsValue(RED));
        assertTrue(cm.containsValue(BRIGHT_GREEN));
        assertTrue(cm.containsValue(BG_RED));
        assertTrue(cm.containsValue(RESET));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + "TestTestTestTest" + RESET,
        "Blablabl," + RED + "TestTestTest" + BRIGHT_GREEN + " Test Test Test " + RESET + "Blaaaa!"})
    @DisplayName("Color Code Mapping - Multiple colors - Exact mapping")
    void getColorCodes_multipleASCIIColorCode_correctColorTreeMap(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        TreeMap<Integer, String> aux = new TreeMap<>();
        aux.put(9, RED);
        aux.put(26, BRIGHT_GREEN);
        aux.put(47, RESET);
        assertEquals(aux, cm);
    }

}
