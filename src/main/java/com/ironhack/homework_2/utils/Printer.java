package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.Account;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Printer {
    // Maybe create a Enum called color pallet?     // TODO [JA] - Create enum with values?
    private static final int PROGRAM_WIDTH = 100;

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

    private static final String BORDER_COLOR = ANSI_BRIGHT_BG_BLUE;
    private static final String HIGHLIGHT_COLOR = ANSI_BRIGHT_BLUE;
    private static final String INSERT_HIGHLIGHT_COLOR = ANSI_BRIGHT_YELLOW;

    public static void printCenterString(String text, int width, String borderStyle, String contentStyle) {
        // Divide the text if it is larger than program width. Uses recursion!
        String newText = divideText(text, width - 3);

        while (!newText.equals(text.trim())) {
            printCenterString(newText, width, borderStyle, contentStyle);

            text = text.trim().substring(newText.length());

            if (newText.contains("\u001B") && !newText.contains("\u001B[0m"))
                text = newText.substring(newText.indexOf("\u001B"), newText.indexOf("\u001B") + 5) + text;

            newText = divideText(text, width - 3);
        }
        // Prints the given text centered by defining empty space and dividing it by 2 for each side. Stylizes the text.
        StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + contentStyle);
        int emptySpaces = width - text.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length() - 2;
        boolean even = emptySpaces % 2 == 0;
        str.append(String.join("", Collections.nCopies(emptySpaces / 2, " ")));
        str.append(text);
        str.append(String.join("", Collections.nCopies(even ? emptySpaces / 2 : (emptySpaces / 2) + 1, " ")));
        str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
        System.out.println(str);
    }

    public static void printLeftString(String text, int leftSpaces, int width, String borderStyle, String contentStyle) {
        // Divide the text if it is larger than program width. Uses recursion!
//    System.out.println(text);
//    System.out.println(text.length());

        String newText = divideText(text, width - leftSpaces - 3);
        while (!newText.equals(text.trim())) {
            printLeftString(newText, leftSpaces, width, borderStyle, contentStyle);
            text = text.trim().substring(newText.length());
            if (newText.contains("\u001B") && !newText.contains("\u001B[0m"))
                text = newText.substring(newText.indexOf("\u001B"), newText.indexOf("\u001B") + 5) + text;
            newText = divideText(text, width - leftSpaces - 3);
        }
        // Prints the given text centered by defining empty space and dividing it by 2 for each side. Stylizes the text.
        StringBuilder str = new StringBuilder(borderStyle + " " + ANSI_RESET + contentStyle);
        int rightSpaces = width - text.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -\\/]*[@-~]", "").length() - leftSpaces - 2;
        str.append(String.join("", Collections.nCopies(leftSpaces, " ")));
        str.append(text);
        str.append(String.join("", Collections.nCopies(rightSpaces, " ")));
        str.append(ANSI_RESET).append(borderStyle).append(" ").append(ANSI_RESET);
        System.out.println(str);
    }


    public static void print(String text) {
        printLeftString(text, 4, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    public static void print(String text, int margin) {
        printLeftString(text, margin, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    public static void printFull(String text) {
        printLeftString(text, 4, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    public static void printFull(String text, int margin) {
        printLeftString(text, margin, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    public static void printProgramTitle() {
        printCenterString("CRM by Ben the Warrior", PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR + ANSI_BLACK);
    }

    public static void printEmptyLine() {
        printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, "");
    }

    public static void printFullLine() {
        printLeftString("", 0, PROGRAM_WIDTH, BORDER_COLOR, BORDER_COLOR);
    }

    public static void printNothing() {
        System.out.println();
    }

    public static String printWarning() {
        return (ANSI_YELLOW + "[WARNING] " + ANSI_RESET);
    }

    public static String printError() {
        return (ANSI_RED + "[ERROR] " + ANSI_RESET);
    }


    public static String divideText(String text, int emptySpaces) {   // TODO [JA] - Maybe move this to Utils class
        if (text.trim().length() <= emptySpaces) return text.trim();
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

    public static void clearCommandLine() throws IOException, InterruptedException {
        // Clear in Intellij
        System.out.println("==========================================================================================");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // TODO [JA] - Need to test both in different OS
        // Method 1
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Method 2
//    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); //throws IOException and InterruptedException
    }


    // ======================================== CREATE MAIN MENU ========================================
    public static void printMainMenu() {
        printProgramTitle();
        printEmptyLine();
        print("Welcome to Ben's CMR program. Here are the main commands:");
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "create lead" + ANSI_RESET + " - Creates a new Lead");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "convert <ID>" + ANSI_RESET + " - Converts a Lead into an Opportunity");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "close won <ID>" + ANSI_RESET + " - Close Won Opportunity");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "close lost <ID>" + ANSI_RESET + " - Close Lost Opportunity");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "lookup <OBJECT> <ID>" + ANSI_RESET + " - Search for specific Lead, Opportunity, Account or Contact");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "show <OBJECT PLURAL>" + ANSI_RESET + " - List all Leads, Opportunities, Accounts or Contacts");
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "help h" + ANSI_RESET + " - Explains usage of available commands");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "save s" + ANSI_RESET + " - Saves the changed data");
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "exit x" + ANSI_RESET + " - Saves and exits  the program");
        printEmptyLine();
        printFullLine();
    }


    // ======================================== CREATE LEAD MENU ========================================
    public static void printCreateLeadMenu() {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Name: ");
        printEmptyLine();
        print("Email: ");
        printEmptyLine();
        print("Phone Number: ");
        printEmptyLine();
        print("Company Name: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Lead Name: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateLeadMenu(String name) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Lead" + ANSI_RESET);
        printEmptyLine();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        printEmptyLine();
        print("Email: ");
        printEmptyLine();
        print("Phone Number: ");
        printEmptyLine();
        print("Company Name: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Lead Email: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateLeadMenu(String name, String email) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        printEmptyLine();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        printEmptyLine();
        print("Phone Number: ");
        printEmptyLine();
        print("Company Name: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Lead Phone Number: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateLeadMenu(String name, String email, String phoneNr) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        printEmptyLine();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        printEmptyLine();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + phoneNr + ANSI_RESET);
        printEmptyLine();
        print("Company Name: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Lead Company Name: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateLeadMenu(String name, String email, String phoneNr, String companyName) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        printEmptyLine();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        printEmptyLine();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + phoneNr + ANSI_RESET);
        printEmptyLine();
        print("Company Name: " + INSERT_HIGHLIGHT_COLOR + companyName + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Lead" + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }


    // ======================================== CREATE OPPORTUNITY MENU ========================================
    public static void printCreateOpportunityMenu() {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Product: ");
        printEmptyLine();
        print("Quantity: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Product type [HYBRID, FLATBED or BOX]: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateOpportunityMenu(Product product) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Product: " + INSERT_HIGHLIGHT_COLOR + product + ANSI_RESET);
        printEmptyLine();
        print("Quantity: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Quantity: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateOpportunityMenu(Product product, int quantity, String contactName) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Product: " + INSERT_HIGHLIGHT_COLOR + product + ANSI_RESET);
        printEmptyLine();
        print("Quantity: " + INSERT_HIGHLIGHT_COLOR + quantity + ANSI_RESET);
        printEmptyLine();
        print("Contact Name: " + INSERT_HIGHLIGHT_COLOR + contactName + ANSI_RESET); // it can be the lead name if complicated we can delete
        printEmptyLine();
        print("Status: " + INSERT_HIGHLIGHT_COLOR + Status.OPEN + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Opportunity" + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    // ======================================== CREATE ACCOUNT MENU ========================================

    public static void printCreateAccountMenu() {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: ");
        printEmptyLine();
        print("Number of Employees: ");
        printEmptyLine();
        print("City: ");
        printEmptyLine();
        print("Country: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER]: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateAccountMenu(Industry industry) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        printEmptyLine();
        print("Number of Employees: ");
        printEmptyLine();
        print("City: ");
        printEmptyLine();
        print("Country: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Number of Employees: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        printEmptyLine();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        printEmptyLine();
        print("City: ");
        printEmptyLine();
        print("Country: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert City: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount, String city) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        printEmptyLine();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        printEmptyLine();
        print("City: " + INSERT_HIGHLIGHT_COLOR + city + ANSI_RESET);
        printEmptyLine();
        print("Country: ");
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Insert Country: " + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount, String city, String country) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        printEmptyLine();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        printEmptyLine();
        print("City: " + INSERT_HIGHLIGHT_COLOR + city + ANSI_RESET);
        printEmptyLine();
        print("Country: " + INSERT_HIGHLIGHT_COLOR + country + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Account" + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }


    public static void printAccountInfo(Account account) {
        printProgramTitle();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "Account ID - " + account.getId() + HIGHLIGHT_COLOR);
        printEmptyLine();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + account.getIndustry() + ANSI_RESET);
        printEmptyLine();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + account.getEmployeeCount() + ANSI_RESET);
        printEmptyLine();
        print("City: " + INSERT_HIGHLIGHT_COLOR + account.getCity() + ANSI_RESET);
        printEmptyLine();
        print("Country: " + INSERT_HIGHLIGHT_COLOR + account.getCountry() + ANSI_RESET);
        printEmptyLine();
        print("Contacts: " + INSERT_HIGHLIGHT_COLOR + account.getContactList() + ANSI_RESET);
        printEmptyLine();
        print("Opportunities: " + INSERT_HIGHLIGHT_COLOR + account.getOpportunityList() + ANSI_RESET);
        printEmptyLine();
        printEmptyLine();
        printEmptyLine();
        print(HIGHLIGHT_COLOR + "ENTER - Go Back" + HIGHLIGHT_COLOR);
        printEmptyLine();
        printEmptyLine();
        printFullLine();
    }


}
