package com.ironhack.homework_2.utils;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Collections;

public class Printer {
  // Maybe create a Enum called color pallet?     // TODO [JA] - Create enum with values.
  private static final int PROGRAM_WIDTH = 120;

  // ==================== Text Colors ====================
  private static final String ANSI_RESET = "\u001B[0m";

  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
  public static final String ANSI_BRIGHT_RED = "\u001B[91m";
  public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
  public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
  public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
  public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
  public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
  public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

  // ==================== Background Colors ====================
  public static final String ANSI_BG_BLACK = "\u001B[40m";
  public static final String ANSI_BG_RED = "\u001B[41m";
  public static final String ANSI_BG_GREEN = "\u001B[42m";
  public static final String ANSI_BG_YELLOW = "\u001B[43m";
  public static final String ANSI_BG_BLUE = "\u001B[44m";
  public static final String ANSI_BG_PURPLE = "\u001B[45m";
  public static final String ANSI_BG_CYAN = "\u001B[46m";
  public static final String ANSI_BG_WHITE = "\u001B[47m";
  public static final String ANSI_BRIGHT_BG_BLACK = "\u001B[100m";
  public static final String ANSI_BRIGHT_BG_RED = "\u001B[101m";
  public static final String ANSI_BRIGHT_BG_GREEN = "\u001B[102m";
  public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
  public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";
  public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
  public static final String ANSI_BRIGHT_BG_CYAN = "\u001B[106m";
  public static final String ANSI_BRIGHT_BG_WHITE = "\u001B[107m";

  private static final String BORDER_COLOR = ANSI_BG_YELLOW;


  public static void printCenterString(String text, int width, String borderStyle, String contentStyle) {
    if (text.length() > width - 4) {      //split text to other rows
      text = text.substring(0, width - 4);
      System.out.println(printWarning() + "String must fit the program width of " + PROGRAM_WIDTH + " characters.");
    }
    StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + contentStyle);
    int emptySpaces = width - text.length() - 2;
    boolean even = emptySpaces % 2 == 0;
    str.append(String.join("", Collections.nCopies(emptySpaces / 2, " ")));
    str.append(text);
    str.append(String.join("", Collections.nCopies(even ? emptySpaces / 2 : (emptySpaces / 2) + 1, " ")));
    str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
    System.out.println(str);
  }

  public static void printLeftString(String text, int leftSpaces, int width, String borderStyle, String contentStyle) {
    if (text.length() > width - leftSpaces - 4) {
      text = text.substring(0, width - leftSpaces - 4);
      System.out.println(printWarning() + "String must fit the program width of " + PROGRAM_WIDTH + " characters.");
    }
    StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + contentStyle);
    int rightSpaces = width - text.length() - leftSpaces - 2;
    str.append(String.join("", Collections.nCopies(leftSpaces, " ")));
    str.append(text);
    str.append(String.join("", Collections.nCopies(rightSpaces, " ")));
    str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
    System.out.println(str);
  }


  public static void printProgramTitle(String title) {
    printCenterString(title, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR + ANSI_BLACK);
  }

  public static void printEmptyLine() {
    printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, "");
  }

  public static void printFullLine() {
    printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
  }

  public static void printLine(String text) {


    printLeftString(text, 4, PROGRAM_WIDTH, BORDER_COLOR, "");
  }

  public static String divideText(String text, int emptySpaces) {
    if (text.trim().length() <= emptySpaces) return text.trim();
    String[] textArray = text.trim().split(" ");
    StringBuilder smallText = new StringBuilder();
    int countLength = 0;
    for (String word : textArray) {
      countLength = word.length() + 1;
      if (countLength > emptySpaces) return smallText.toString();
      smallText.append(word).append(" ");
    }
    return "";
  }


  public static String printWarning() {
    return (ANSI_YELLOW + "[WARNING] " + ANSI_RESET);
  }

  public static String printError() {
    return (ANSI_RED + "[ERROR] " + ANSI_RESET);
  }

  public static void clearCommandLine() throws IOException, InterruptedException {
    // Clear in Intellij
    System.out.println("==========================================================================================");
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

    // TODO [JA] - Need to test both in diferent OS
    // Method 1
    System.out.print("\033[H\033[2J");
    System.out.flush();

    // Method 2
//    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //throws IOException and InterruptedException
  }

}
