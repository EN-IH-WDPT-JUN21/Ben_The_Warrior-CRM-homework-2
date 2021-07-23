// Printer class structure:
// 1. Printer proprieties and defined colors
// 2. Getters and setters
// 3. Simplified printers (printer methods that are used directly in the project)
// 4. Main printers (Are the primary methods that print everything. Are used by the simplified printers to simplify code and keep the same style)
// 5. Aux methods (complement the main printers functionalities)

package com.ironhack.homework_2.utils;

import java.io.IOException;
import java.util.Collections;

public class Printer {
    // Maybe create a Enum called color pallet?     // TODO [JA] - Create enum with values? Maybe delete the ones not in use
    private static final int PROGRAM_WIDTH = 100;

    private static final String ANSI_RESET = "\u001B[0m";
    // ==================== Text Colors ====================
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

    private static final String BORDER_COLOR = ANSI_BRIGHT_BG_BLUE;
    private static final String HIGHLIGHT_COLOR = ANSI_BRIGHT_BLUE;
    private static final String INSERT_HIGHLIGHT_COLOR = ANSI_BRIGHT_YELLOW;


    // ======================================== 2. GETTERS AND SETTERS ========================================

    public static String getAnsiReset() {
        return ANSI_RESET;
    }

    public static String getHighlightColor() {
        return HIGHLIGHT_COLOR;
    }

    public static String getInsertHighlightColor() {
        return INSERT_HIGHLIGHT_COLOR;
    }


    // ======================================== 3. SIMPLIFIED PRINTERS ========================================

    // Print in the main style. Side borders colored and text with a margin to the left.
    public static void print() {
        printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    public static void print(String text) {
        printLeftString(text, 4, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    public static void print(String text, int margin) {
        printLeftString(text, margin, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    // Print line with full background color. Side borders and content with color and text with a margin to the left.
    public static void printFull() {
        printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    public static void printFull(String text) {
        printLeftString(text, 4, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    public static void printFull(String text, int margin) {
        printLeftString(text, margin, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    // Print program title colored. Filled with color and text black.
    public static void printProgramTitle() {
        printCenterString("CRM by Ben the Warrior", PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR + ANSI_BLACK);
    }

    // Print line without formatting
    public static void printEmpty() {
        System.out.println();
    }

    // Return a colored Warning without new line. (useful for presenting small error situations)
    public static String printWarning() {
        return (ANSI_YELLOW + "[WARNING] " + ANSI_RESET);
    }

    // Return a colored Error without new line. (useful for presenting error situations)
    public static String printError() {
        return (ANSI_RED + "[ERROR] " + ANSI_RESET);
    }


    // ======================================== 4. MAIN PRINTERS ========================================

    // Clear command line. In Intellij adds spacing between menus.
    public static void clearCommandLine() /*throws IOException, InterruptedException*/ {
        // Clear in Intellij
        System.out.println("==========================================================================================");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // TODO [JA] - Need to test both in different OS
        // Clear in command line - Method 1
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Clear in command line - Method 2
//    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //throws IOException and InterruptedException
    }

    // Print a stylish text line with the text centered.
    // If text exceeds program width, wraps to the next line, no cut words. Uses recursion!
    // If text has color, keeps it to next line. (Used for printing variable size text)
    // Uses recursion to print the different lines. (uses aux method -> divideText(text, maxWidth) )
    public static void printCenterString(String text, int width, String borderStyle, String innerStyle) {
        // Divide the text if it is larger than program width.
        String newText = divideText(text, width - 3);
        // Recursion!
        while (!newText.equals(text.trim())) {  //loops while it is larger than width
            printCenterString(newText, width, borderStyle, innerStyle);  //prints first line
            text = text.trim().substring(newText.length());  //removes first line
            if (newText.contains("\u001B") && !newText.contains("\u001B[0m"))  //adds style if it has it (not the reset style)
                text = newText.substring(newText.indexOf("\u001B"), newText.indexOf("\u001B") + 5) + text;
            newText = divideText(text, width - 3);  //divides new text if larger
        }
        // Prints the given text centered by defining empty space and dividing it by 2 for each side. Stylizes the text.
        StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + innerStyle);
        int emptySpaces = width - text.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length() - 2; // empty spaces don't count the ANSIS codes
        boolean even = emptySpaces % 2 == 0;
        str.append(String.join("", Collections.nCopies(emptySpaces / 2, " ")));
        str.append(text);
        str.append(String.join("", Collections.nCopies(even ? emptySpaces / 2 : (emptySpaces / 2) + 1, " ")));
        str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
        System.out.println(str);
    }

    // Print a stylish text line with the text on the left. Has a margin option.
    // If text exceeds program width, wraps to the next line, no cut words. Uses recursion!
    // If text has color, keeps it to next line. (Used for printing variable size text)
    // Uses recursion to print the different lines. (uses aux method -> divideText(text, maxWidth) )
    public static void printLeftString(String text, int leftSpaces, int width, String borderStyle, String contentStyle) {
        // Divide the text if it is larger than program width.
        String newText = divideText(text, width - leftSpaces - 3);
        // Recursion!
        while (!newText.equals(text.trim())) {  //loops while it is larger than width
            printLeftString(newText, leftSpaces, width, borderStyle, contentStyle);  //prints first line
            text = text.trim().substring(newText.length());  //removes first line
            if (newText.contains("\u001B") && !newText.contains("\u001B[0m"))  //adds style if it has it (not the reset style)
                text = newText.substring(newText.indexOf("\u001B"), newText.indexOf("\u001B") + 5) + text;
            newText = divideText(text, width - leftSpaces - 3); //divides new text if larger
        }
        // Prints the given text by defining empty spaces for the right and left side. Stylizes the text.
        StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + contentStyle);
        int rightSpaces = width - text.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length() - leftSpaces - 2; // empty spaces don't count the ANSIS codes
        str.append(String.join("", Collections.nCopies(leftSpaces, " ")));
        str.append(text);
        str.append(String.join("", Collections.nCopies(rightSpaces, " ")));
        str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
        System.out.println(str);
    }


    // ======================================== 5. AUX METHODS ========================================

    public static String divideText(String text, int emptySpaces) {   // TODO [JA] - Maybe move this to Utils class
        if (text.trim().length() <= emptySpaces) return text.trim(); // If input size fits program with returns input.
        String[] textArray = text.trim().split(" ");

        // Return entire words of text that fit into empty spaces:
        StringBuilder smallText = new StringBuilder();
        int countLength = 0;
        for (String word : textArray) {
            // check if word length is greater than empty space and first word.
            // If yes it will return only the part that fits.
            if (word.length() > emptySpaces && countLength == 0) return word.substring(0, emptySpaces - 1);

            // Count words length and adds word to output text until it surpasses the empty spaces. Returns when full
            countLength += word.length() + 1;
            if (countLength > emptySpaces) return smallText.toString().trim();
            smallText.append(word).append(" ");
        }
        return "";
    }

}
