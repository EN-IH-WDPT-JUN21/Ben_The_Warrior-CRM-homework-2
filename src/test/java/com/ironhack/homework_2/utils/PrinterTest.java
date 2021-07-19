package com.ironhack.homework_2.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

  @ParameterizedTest
  @ValueSource(strings = {"", " ", "All 1234 12345", "ad+hgkmsao jghnworyn0a35yn03 ahny 39ahn 39ngv 3agn     "})
  void divideText_textSmallerOrEqualThanSpace_returnAllTextTrimmed(String text) {
    assertEquals(text.trim(), Printer.divideText(text, text.length() + 1));
    assertEquals(text.trim(), Printer.divideText(text, text.length()));
  }

  @ParameterizedTest
  @ValueSource(strings = {"All 1234 12345", "ad+hgkmsao jghnworyn0a35yn03 ahny 39ahn 39ngv 3agn     ",
      "wnfvc924nfg2gh 2hrf 284y3hf 183y bdh1bc y81"})
  void divideText_textLargerThanSpace_returnTextSmallerThanSpace(String text) {
    assertTrue(0 >= Printer.divideText(text, 0).length());
    assertTrue(2 >= Printer.divideText(text, 2).length());
    assertTrue(20 >= Printer.divideText(text, 20).length());
    assertTrue(40 >= Printer.divideText(text, 30).length());
    assertTrue(120 >= Printer.divideText(text, 80).length());
  }

  @ParameterizedTest
  @ValueSource(strings = {"1234 3562 1356 sfhs", "1234 du5d sg4g 4gs4","rair 3462 2136 2gs4","2d23 sfrs rway afjja", })
  void divideText_textLargerThanSpace_doNotSplitWords_inFours(String text) {
    assertEquals(text.substring(0,4).trim(), Printer.divideText(text, 6));
    assertEquals(text.substring(0,14).trim(), Printer.divideText(text, 18));
  }

  @ParameterizedTest
  @ValueSource(strings = {"12345 35625 13565 sfhs5", "12345 du5d5 sg4g 4gs4","rair5 34625 21365 2gs45","2d235 sfrs5 rway5 afjja", })
  void divideText_textLargerThanSpace_doNotSplitWords_inFives(String text) {
    assertEquals(text.substring(0,5).trim(), Printer.divideText(text, 6));
    assertEquals(text.substring(0,17).trim(), Printer.divideText(text, 18));
  }






  @Test
  void printCenterString() {
  }

  @Test
  void printLeftString() {
  }

  @Test
  void printProgramTitle() {
  }

  @Test
  void printEmptyLine() {
  }

  @Test
  void printFullLine() {
  }

  @Test
  void printLine() {
  }


  @Test
  void printWarning() {
  }

  @Test
  void printError() {
  }

  @Test
  void clearCommandLine() {
  }
}