package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private final String RED = "\u001B[31m";
    private final String RESET = "\u001B[0m";
    private final String BRIGHT_GREEN = "\u001B[92m";
    private final String BG_RED = "\u001B[41m";

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


    // ============================== TEST divideText() ==============================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    ", "TestTest", " Test Test", "Test Test ", "TestTestTest", "Test Test Test",
        " Test Test t", "TestTestTestTestTestTestTest", "Test test TestTest Test Test",
        "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest Test "})
    void divideText_textEqualOrSmallerThanEmptySpace_originalStringAndEmptyString(String s) {
        String[] dividedString = Printer.divideText(s, s.length());
        assertTrue(dividedString[0].equals(s) && dividedString[1].equals(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest", "TestTestTest", "TestTestTestTestTestTestTest",
        "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest Test "})
    void divideText_firstWordLargerThanEmptySpace_firstWordSplitByEmptySpaceAndRemainingStringExists(String s) {
        String[] dividedString = Printer.divideText(s, 3);
        assertTrue(dividedString[0].equals("Tes") && dividedString[1].length() > 0);
    }

    @Test
    void divideText_firstWordLargerThanEmptySpace_correctSplitText() {
        String[] dividedString = Printer.divideText("TestTestTest Test", 6);
        assertTrue(dividedString[0].equals("TestTe") && dividedString[1].equals("stTest Test"));
    }

    @ParameterizedTest
    @ValueSource(strings = {" Test Test", "Test Test ", "Test Test Test", " Test Test t",
        "Test test TestTest Test Test"})
    void divideText_textLargerThanEmptySpace_wordsThatFitAndRemainingStringExists(String s) {
        String[] dividedString = Printer.divideText(s, 6);
        assertTrue(dividedString[0].equals("Test ") && dividedString[1].length() > 0);
    }

    @Test
    void divideText_textLargerThanEmptySpace_correctSplitText() {
        String[] dividedString = Printer.divideText("Test Test TestTest", 12);
        assertTrue(dividedString[0].equals("Test Test ") && dividedString[1].equals("TestTest"));
    }

    // ============================== TEST numberOfTextRows() ==============================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "TestTest", " Test Test", "Test Test "})
    void numberOfTextRows_textSmallerThanEmptySpace_OneRow(String s) {
        assertEquals(1, Printer.numberOfTextRows(s, 10));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTestTest", "Test Test Test", " Test Test t", "TestTestTestTestTestTestTest",
        "Test test TestTest Test Test"})
    void numberOfTextRows_textLargerThanEmptySpace_moreThanOneRow(String s) {
        assertTrue(Printer.numberOfTextRows(s, 10) > 1);
    }

    @ParameterizedTest
    @CsvSource({"6,10", "10,5", "12,5", "18,4", "19,3", "24,3", "30,2", "49,1", "100,1"})
    void numberOfTextRows_fixedTextWithVariableEmptySpace_correctNumberOfRows(int inputEmptySpace, int expectedRows) {
        String s = "Test Test Test Test Test Test Test Test Test Test";
        assertEquals(expectedRows, Printer.numberOfTextRows(s, inputEmptySpace));
    }

    // ============================== TEST getColorCodes() ==============================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     ", "Test", "TestTestTestTestTestTestTest",
        "TestTest TestTestTest - TestTest: Test? `\"", "-931t3tn 9qcg7637 613",
        "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613"})
    void getColorCodes_noASCIIColorCode_EmptyTreeMap(String s) {
        assertTrue(Printer.getColorCodes(s).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {RED + "" + RESET, RED + " " + RESET, RED + "Test" + RESET,
        RED + "TestTestTestTestTestTestTest" + RESET, RED + "TestTest TestTestTest - TestTest: Test? `\"" + RESET,
        RED + "-931t3 tn9qcg7 637613" + RESET,
        RED + "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613" + RESET})
    void getColorCodes_twoASCIIColorCode_TreeMapWithSizeTwo(String s) {
        assertEquals(2, Printer.getColorCodes(s).size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + RESET,
        "Blablaba " + RED + "TestTestTest" + RESET + " TestTestTest"})
    void getColorCodes_twoASCIIColorCodeInIndex9And26_correctColorCodeIndexes(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertEquals(9, cm.firstKey());
        assertEquals(26, cm.lastKey());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + BG_RED + "Lalallalalaala" + RESET,
        "Blablaba " + RED + "TestTestTest" + BRIGHT_GREEN + " TestTestTest" + BG_RED + "Lalalala" + RESET})
    void getColorCodes_multipleASCIIColorCode_savesAllColorCode(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertTrue(cm.containsValue(RED));
        assertTrue(cm.containsValue(BRIGHT_GREEN));
        assertTrue(cm.containsValue(BG_RED));
        assertTrue(cm.containsValue(RESET));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + "TestTestTestTest" + RESET,
        "Blablabla" + RED + "TestTestTest" + BRIGHT_GREEN + " Test Test Test " + RESET + "Blaaaa!"})
    void getColorCodes_multipleASCIIColorCode_correctColorTreeMap(String s) {
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        TreeMap<Integer, String> aux = new TreeMap<>();
        aux.put(9, RED);
        aux.put(26, BRIGHT_GREEN);
        aux.put(47, RESET);
        assertEquals(aux, cm);
    }


// =====================================================================================================


    @ParameterizedTest
    @ValueSource(strings = {"All 1234 12345", "ad+hgkmsao jghnworyn0a35yn03 ahny 39ahn 39ngv 3agn     ",
        "wnfvc924nfg2gh 2hrf 284y3hf 183y bdh1bc y81"})
    void divideText_textLargerThanSpace_returnTextSmallerThanSpace(String text) {
//    assertTrue(0 >= Printer.divideText(text, 0).length());
//    assertTrue(2 >= Printer.divideText(text, 2).length());
//    assertTrue(20 >= Printer.divideText(text, 20).length());
//    assertTrue(40 >= Printer.divideText(text, 30).length());
//    assertTrue(120 >= Printer.divideText(text, 80).length());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234 3562 1356 sfhs", "1234 du5d sg4g 4gs4", "rair 3462 2136 2gs4", "2d23 sfrs rway afjja",})
    void divideText_textLargerThanSpace_doNotSplitWords_inFours(String text) {
//    assertEquals(text.substring(0,4).trim(), Printer.divideText(text, 6));
//    assertEquals(text.substring(0,14).trim(), Printer.divideText(text, 18));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345 35625 13565 sfhs5", "12345 du5d5 sg4g 4gs4", "rair5 34625 21365 2gs45", "2d235 sfrs5 rway5 afjja",})
    void divideText_textLargerThanSpace_doNotSplitWords_inFives(String text) {
//    assertEquals(text.substring(0,5).trim(), Printer.divideText(text, 6));
//    assertEquals(text.substring(0,17).trim(), Printer.divideText(text, 18));
    }


}