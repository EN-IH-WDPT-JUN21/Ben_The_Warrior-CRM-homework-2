package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrinterTest {
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BG_RED = "\u001B[41m";


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    // ============================== Test getColorCodes() ==============================
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     ", "Test", "TestTestTestTestTestTestTest",
        "TestTest TestTestTest - TestTest: Test? `\"", "-931t3tn 9qcg7637 613",
        "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613"})
    void testGetColorCodes_noASCIIColorCode_EmptyTreeMap(String s) {
//        System.out.println(Printer.getColorCodes(s));
        assertTrue(Printer.getColorCodes(s).isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {RED + "" + RESET, RED + " " + RESET, RED + "Test" + RESET,
        RED + "TestTestTestTestTestTestTest" + RESET, RED + "TestTest TestTestTest - TestTest: Test? `\"" + RESET,
        RED + "-931t3 tn9qcg7 637613" + RESET,
        RED + "-93jkhg9qn3v9w783gh q783ghq8v3 gq3y8igbq58y3bgakgba3jygbxnev853 g3ma8yg nuebv antg ye ng vzyug ajyv znrut3 gegz1t3 tn9qcg7 637613" + RESET})
    void testGetColorCodes_twoASCIIColorCode_TreeMapWithSizeTwo(String s) {
//        System.out.println(Printer.getColorCodes(s));
        assertEquals(2, Printer.getColorCodes(s).size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + RESET,
        "Blablaba " + RED + "TestTestTest" + RESET + " TestTestTest"})
    void testGetColorCodes_twoASCIIColorCodeInIndex9And26_correctColorCodeIndexes(String s) {
//        System.out.println(Printer.getColorCodes(s));
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertEquals(9, cm.firstKey());
        assertEquals(26, cm.lastKey());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + BG_RED + "Lalallalalaala" + RESET,
        "Blablaba " + RED + "TestTestTest" + BRIGHT_GREEN + " TestTestTest" + BG_RED + "Lalalala" + RESET})
    void testGetColorCodes_multipleASCIIColorCode_savesAllColorCode(String s) {
//        System.out.println(Printer.getColorCodes(s));
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        assertTrue(cm.containsValue(RED));
        assertTrue(cm.containsValue(BRIGHT_GREEN));
        assertTrue(cm.containsValue(BG_RED));
        assertTrue(cm.containsValue(RESET));
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestTest " + RED + "TestTestTest" + BRIGHT_GREEN + "TestTestTestTest" + RESET,
        "Blablabla" + RED + "TestTestTest" + BRIGHT_GREEN + " Test Test Test " + RESET + "Blaaaa!"})
    void testGetColorCodes_multipleASCIIColorCode_correctColorTreeMap(String s) {
//        System.out.println(Printer.getColorCodes(s));
        TreeMap<Integer, String> cm = Printer.getColorCodes(s);
        TreeMap<Integer, String> aux = new TreeMap<>();
        aux.put(9, RED);
        aux.put(26, BRIGHT_GREEN);
        aux.put(47, RESET);
        assertEquals(aux, cm);
    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "All 1234 12345", "ad+hgkmsao jghnworyn0a35yn03 ahny 39ahn 39ngv 3agn     "})
    void divideText_textSmallerOrEqualThanSpace_returnAllTextTrimmed(String text) {
//    assertEquals(text.trim(), Printer.divideText(text, text.length() + 1));
//    assertEquals(text.trim(), Printer.divideText(text, text.length()));
    }

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