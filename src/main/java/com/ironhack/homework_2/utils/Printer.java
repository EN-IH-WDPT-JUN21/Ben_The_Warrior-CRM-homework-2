// TODO JA: Small intro and index structure of class
// Printer class structure:
// 1. Printer proprieties and defined colors
// 2. Getters and setters
// 3. Simplified printers (printer methods that are used directly in the project)
// 4. Main printers (Are the primary methods that print everything. Are used by the simplified printers to simplify code and keep the same style)
// 5. Aux methods (complement the main printers functionalities)

package com.ironhack.homework_2.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Printer {
    private static final int PROGRAM_WIDTH = 150;
    private static final int PROGRAM_HEIGHT = 25;
    private static final int BORDER_WIDTH = 2;
    private static final int BORDER_TO_TEXT_SPACES = 4;
    private static final String ANSI_RESET = "\u001B[0m";
    // ==================== Text Colors ====================
    private static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_BRIGHT_RED = "\u001B[91m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    // ==================== Background Colors ====================
    public static final String ANSI_BRIGHT_BG_BLUE = "\u001B[104m";

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

    public static int getProgramHeight() {
        return PROGRAM_HEIGHT;
    }


    // ======================================== 3. SIMPLIFIED PRINTERS ========================================

    public static void print(String text) {
        printLeftString(text, BORDER_TO_TEXT_SPACES, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    // Print line with full background color. Side borders and content with color and text with a margin to the left.
    public static void printFull() {
        printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    // Print program title colored. Filled with color and text black.
    public static void printProgramTitle() {
        printFull();
        printCenterString("CRM by Ben the Warrior", PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR + ANSI_BLACK);
        printFull();
    }

    // ======================================== 4. MAIN PRINTERS ========================================
    // Clear command line. In Intellij adds spacing between menus.
    public static void clearCommandLine() /*throws IOException, InterruptedException*/ {
        // Clear in Intellij
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        // Clear in command line
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Print a stylish text line with the text centered.
    // If text exceeds program width, wraps to the next line, no cut words. Uses recursion!
    // If text has color, keeps it to next line. (Used for printing variable size text)
    // Uses recursion to print the different lines. (uses aux method -> divideText(text, maxWidth) )
    public static void printCenterString(String text, int width, String borderStyle, String innerStyle) {
        // Divides input text in multiple rows.
        ArrayList<String> rows = createTextRows(text, width - 2 * BORDER_WIDTH);
        // Prints all rows stylized.
        for (String row : rows) {
            StringBuilder str = new StringBuilder();
            str.append(borderStyle).append(String.join("", Collections.nCopies(BORDER_WIDTH, " "))).append(ANSI_RESET).append(innerStyle);
            // Empty spaces on the right. Doesn't count color codes
            int emptySpaces = width - row.replaceAll("\u001B\\[[;\\d]*m", "").length() - 2 * BORDER_WIDTH;
            boolean even = emptySpaces % 2 == 0;
            // Half the empty spaces are adjusted
            str.append(String.join("", Collections.nCopies(even ? emptySpaces / 2 : (emptySpaces / 2) + 1, " ")));
            str.append(row);
            // Other half the empty spaces
            str.append(String.join("", Collections.nCopies(emptySpaces / 2, " ")));
            str.append(ANSI_RESET).append(borderStyle).append(String.join("", Collections.nCopies(BORDER_WIDTH, " "))).append(ANSI_RESET);
            System.out.println(str);
        }
    }


    // Print a stylish text line with the text on the left. Has a margin option.
    // If text exceeds program width, wraps to the next line, no cut words unless single big word.
    // If text has color, keeps it to next lines. (Used for printing variable size text)
    public static void printLeftString(String text, int leftSpaces, int width, String borderStyle, String contentStyle) {
        // Divides input text in multiple rows.
        ArrayList<String> rows = createTextRows(text, width - 2 * leftSpaces - 2 * BORDER_WIDTH);
        // Prints all rows stylized.
        for (String row : rows) {
            StringBuilder str = new StringBuilder();
            str.append(borderStyle).append(String.join("", Collections.nCopies(BORDER_WIDTH, " "))).append(ANSI_RESET).append(contentStyle);
            // Empty spaces on the right. Doesn't count color codes
            int rightSpaces = width - row.replaceAll("\u001B\\[[;\\d]*m", "").length() - leftSpaces - 2 * BORDER_WIDTH;
            str.append(String.join("", Collections.nCopies(leftSpaces, " ")));
            str.append(row);
            str.append(String.join("", Collections.nCopies(rightSpaces, " ")));
            str.append(ANSI_RESET).append(borderStyle).append(String.join("", Collections.nCopies(BORDER_WIDTH, " "))).append(ANSI_RESET);
            System.out.println(str);
        }
    }

    // ======================================== 5. AUX METHODS ========================================
    // Defines the different rows that the printLeftString() and printCenterString() methods will print.
    // The emptySpace is defined only for the text section. Ex: printLeftString -> width - 2*leftSpaces.
    public static ArrayList<String> createTextRows(String text, int emptySpaces) {
        // Initializes Maps and lists that will be used in the aux methods to divide rows and keep a color structure
        ArrayList<String> rows = new ArrayList<>();
        TreeMap<Integer, String> colorMap = getColorCodes(text);
        TreeMap<Integer, String> newColorMap = new TreeMap<>();
        StringBuilder stringStart = new StringBuilder(), previousColor = new StringBuilder();
        // Creates a clean text without colors for counting and structure
        // (colors count for string length but doesn't print characters)
        String cleanText = text.replaceAll("\u001B\\[[;\\d]*m", "");
        // Splits the text according to the empty space. [0] split string;[1] remaining of the string.
        String[] splitCleanText = divideText(cleanText, emptySpaces);
        StringBuilder splitText = new StringBuilder(splitCleanText[0]);
        // Add color map to the clean split string.
        // Save used colors to be added to start of the next row.
        // Not used colors save in aux map with adjusted index.
        for (Integer i : colorMap.keySet()) {
            if (i <= splitText.length()) {
                splitText.insert(i, colorMap.get(i));
                previousColor.append(colorMap.get(i));
            } else {
                newColorMap.put(i - splitText.length(), colorMap.get(i));
            }
        }
        // Add to first row
        rows.add(splitText.toString());

        // checks if text is split. For split test repeat previous row creation methods.
        while (!splitCleanText[0].equals(cleanText)) {
            // Reset values for aux variables
            cleanText = splitCleanText[1];
            colorMap = new TreeMap<>();
            colorMap.putAll(newColorMap);
            newColorMap = new TreeMap<>();
            splitCleanText = divideText(cleanText, emptySpaces);
            splitText = new StringBuilder(splitCleanText[0]);
            stringStart.append(previousColor);
            previousColor = new StringBuilder();
            // Repeat previous color mapping methodology. Add color, save used to start, adjust remaining.
            for (Integer i : colorMap.keySet()) {
                if (i <= splitText.length()) {
                    splitText.insert(i, colorMap.get(i));
                    previousColor.append(colorMap.get(i));
                } else {
                    newColorMap.put(i - splitText.length(), colorMap.get(i));
                }
            }
            // Add new row with starter color
            rows.add(stringStart.toString() + splitText);
        }
        return rows; // returns array list of rows.
    }

    // Counts the number of rows that the printLeftString() and printCenterString() methods will create.
    // The emptySpace is defined only for the text section. Ex: printLeftString -> width - 2*leftSpaces.
    public static int numberOfTextRows(String text, int emptySpaces) {
        // Clean text of color codes and split it.
        String cleanText = text.replaceAll("\u001B\\[[;\\d]*m", "");
        String[] splitText = divideText(cleanText, emptySpaces);
        int count = 1;
        // Divides the text until is no longer divided (split = original). Counts each loop.
        while (!splitText[0].equals(cleanText)) {
            count++;
            cleanText = splitText[1];
            splitText = divideText(cleanText, emptySpaces);
        }
        return count;
    }

    public static int numberOfTextRows(String text){
        return numberOfTextRows(text, PROGRAM_WIDTH - 2 * BORDER_WIDTH);
    }

    // Divides text to fill empty spaces. Returns [0] split string, and [1] remaining string.
    public static String[] divideText(String text, int emptySpaces) {
        if (text.trim().length() <= emptySpaces)    // If input size fits program with returns input.
            return new String[]{text, ""};
        // Initiates aux int and string
        int countLength = 0;
        StringBuilder smallText = new StringBuilder();
        // Divide into words. Return entire words of text that fit into empty spaces:
        String[] textArray = text.trim().split(" ");
        for (String word : textArray) {
            // Check if word length is greater than empty space and is first word. If yes it will return only the part that fits.
            if (word.length() > emptySpaces && countLength == 0)
                return new String[]{word.substring(0, emptySpaces), text.substring(emptySpaces)};
            // Count words length and adds word to output text until it surpasses the empty spaces. Returns when full.
            countLength += word.length() + 1;
            if (countLength > emptySpaces)
                return new String[]{smallText.toString(), text.substring(smallText.length())};
            smallText.append(word).append(" ");
        }
        return new String[]{text, ""}; // Only used to have a return. Never used.
    }   // Note: For it to work with the print method and color mapping this method will always count the space after a word.

    // Method to call divideText method when the empty space corresponds to the maximum possible text width
    public static String[] divideText(String text){
        return divideText(text, PROGRAM_WIDTH - 2 * BORDER_WIDTH);
    }

    // Saves and returns all color codes and their indexes in a string.
    public static TreeMap<Integer, String> getColorCodes(String textString) {
        TreeMap<Integer, String> colorCodes = new TreeMap<>();
        int wordLength = 0, i = 0;
        // Finds color codes and saves them until all found.
        while (i != -1) {
            String code = "";
            i = textString.indexOf("\u001B[");
            if (i != -1) { // if it has color code remove initial part of the string and save colorText and index
                code = textString.substring(i, textString.indexOf("m", i) + 1);
                colorCodes.put(i + wordLength, code);
                textString = textString.substring(0, i) + textString.substring(i + code.length());
            }
            wordLength += code.length(); // Add size of colors that were removed
        }
        return colorCodes;
    }
}