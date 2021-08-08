// PrinterMenu class structure:
// 1. Printer proprieties and defined colors from printer class
// 2. Main menu printer
// 3.1 Lead creator menu (create lead <ID>)
// 3.2 Opportunity creator menu (convert <ID>)
// 3.3 Account creator menu (convert <ID>)
// 4. Object information menu (lookup <OBJECT> <ID>)

package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.classes.Account;
import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Status;

import java.util.ArrayList;

public class PrinterMenu extends Printer {
    private static final String ANSI_RESET = getAnsiReset();
    private static final String HIGHLIGHT_COLOR = getHighlightColor();
    private static final String INSERT_HIGHLIGHT_COLOR = getInsertHighlightColor();
    private static final int PRINT_MULTIPLE_OBJECTS_MAX = 13;
    private static final int ERROR_LINE = 23;
    // String array to print the menu
    private static final String[] menuLine = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    // Set one or multiple lines of the menu String array to the String passed in
    public static void setMenuLines(String str, int... lines) {
        for (int line : lines) {
            if (line < getProgramHeight()) {
                menuLine[line] = str;
            }
        }
    }

    // getter for a particular line in the menu String array
    public static String getMenuLine(int ind) {
        if (ind < getProgramHeight()) {
            return menuLine[ind];
        }
        throw new IllegalArgumentException("Index is greater than the applications height!");
    }

    public static int getPrintMultipleObjectsMax() {
        return PrinterMenu.PRINT_MULTIPLE_OBJECTS_MAX;
    }

    // ======================================== 2. CREATE MAIN MENU ========================================
    // Sets appropriate menu String array and prints it
    public static void printMenu(String menuChoice, String... params) {
        switch (menuChoice) {
            case "main":
                mainMenuLines();
                break;
            case "help":
                helpMenuLines();
                break;
            case "exit":
                saveBeforeQuit();
                break;
            case "lead":
                newLeadLines(params);
                break;
            case "convert":
                convertLeadLines(params);
                break;
        }
        clearCommandLine();
        printProgramTitle();
        for (String line : menuLine) {
            print(line);
        }
        printFull();
    }

    // Set the menu String array for the main menu
    private static void mainMenuLines(){
        setMenuLines("",4,6,7,8,9,10,11,12,13,14,15,16,17,18,19);
        setMenuLines("Welcome to Ben's CMR program.",1);
        setMenuLines("Enter " + HIGHLIGHT_COLOR + "help" + ANSI_RESET + " for a list of valid commands!",20);
        setMenuLines("Enter " + HIGHLIGHT_COLOR + "exit" + ANSI_RESET + " to close the application!",21);
    }

    private static void saveBeforeQuit(){
        setMenuLines("",4,6,7,8,9,10,11,12,13,14,15,16,17,18,19);
        setMenuLines("Goodbye.",1);
        setMenuLines("Do you want to save before exiting?", 19);
        setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- save and exit",20);
        setMenuLines(HIGHLIGHT_COLOR + "exit " + ANSI_RESET + "- exit",21);
    }

    // Set the menu String array for the help menu with a list of available commands
    private static void helpMenuLines(){
        setMenuLines("",7,9,11,13,15,16,18,20);
        setMenuLines("Welcome to Ben's CMR program. Here are the main commands:",1);
        setMenuLines(HIGHLIGHT_COLOR + "new lead" + ANSI_RESET + " - Creates a new Lead",4);
        setMenuLines(HIGHLIGHT_COLOR + "convert <ID>" + ANSI_RESET + " - Converts a Lead into an Opportunity",6);
        setMenuLines(HIGHLIGHT_COLOR + "close-won <ID>" + ANSI_RESET + " - Close Won Opportunity",8);
        setMenuLines(HIGHLIGHT_COLOR + "close-lost <ID>" + ANSI_RESET + " - Close Lost Opportunity",10);
        setMenuLines(HIGHLIGHT_COLOR + "lookup <OBJECT> <ID>" + ANSI_RESET + " - Search for specific Lead, Opportunity, Account or Contact",12);
        setMenuLines(HIGHLIGHT_COLOR + "show <OBJECT PLURAL>" + ANSI_RESET + " - List all Leads, Opportunities, Accounts or Contacts",14);
        setMenuLines(HIGHLIGHT_COLOR + "help" + ANSI_RESET + " - Explains usage of available commands",17);
        setMenuLines(HIGHLIGHT_COLOR + "save" + ANSI_RESET + " - Saves the changed data",19);
        setMenuLines(HIGHLIGHT_COLOR + "exit" + ANSI_RESET + " - Saves and exits the program",21);
    }

    // ======================================== 3.1 NEW LEAD MENU ========================================
    // Set the menu String array for the lead creation menu
    private static void newLeadLines(String... params) {
        // Set initial menu for the lead creation
        if (params.length == 0) {
            setMenuLines("", 1, 7, 9, 11, 13, 14, 15, 16, 17, 18, 19, 21);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR, 4);
            setMenuLines("Name: ", 6);
            setMenuLines("Phone Number: ", 8);
            setMenuLines("Email: ", 10);
            setMenuLines("Company Name: ", 12);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Name: " + HIGHLIGHT_COLOR, 20);
        } else if (params.length == 2) {
            // Update the menu for each lead creation part
            switch (params[0].toLowerCase()) {
                case "name":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Phone Number: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "phone":
                    setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 8);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Email: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "email":
                    setMenuLines(getMenuLine(10) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 10);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Company Name: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "company":
                    setMenuLines(getMenuLine(12) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 12);
                    setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- confirm Lead creation | " +
                        HIGHLIGHT_COLOR + "back " + ANSI_RESET + "- cancel Lead creation", 20);
                    break;
            }
        } else {
            throw new IllegalArgumentException("Incorrect number of parameters");
        }
    }

    // ======================================== 3.2 CONVERT LEAD MENU ========================================
    // Set the menu String array for the lead conversion menu
    private static void convertLeadLines(String... params) {
        // Set menu for the new Opportunity creation
        if (params.length == 0) {
            setMenuLines("", 1, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR, 4);
            setMenuLines("Product: ", 6);
            setMenuLines("Quantity: ", 8);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Product type [HYBRID, FLATBED or BOX]: " + HIGHLIGHT_COLOR, 20);
        // Update the menu for the lead conversion
        } else if (params.length == 2) {
            switch (params[0].toLowerCase()) {
                case "product":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Quantity: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "industry":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Employee Count: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "employees":
                    setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 8);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert City: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "city":
                    setMenuLines(getMenuLine(10) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 10);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Country: " + HIGHLIGHT_COLOR, 20);
                    break;
                case "country":
                    setMenuLines(getMenuLine(12) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 12);
                    setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- delete Lead and create Contact, Opportunity and Account | " +
                        HIGHLIGHT_COLOR + "back " + ANSI_RESET + "- cancel Lead conversion", 20);
                    break;
            }
        } else if (params.length == 3 && params[0].toLowerCase().equals("quantity and contact")) {
            setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET, 8);
            setMenuLines("Contact Name: " + INSERT_HIGHLIGHT_COLOR + params[2] + ANSI_RESET, 10);
            setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + Status.OPEN + ANSI_RESET, 12);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- confirm Opportunity information | " + HIGHLIGHT_COLOR + "back " + ANSI_RESET + "- return to the main menu", 20);
        //Set menu for the new Account creation
        } else if (params.length == 1 && params[0].toLowerCase().equals("account")) {
            setMenuLines("", 1, 7, 9, 11, 13, 14, 15, 16, 17, 18, 19, 21);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR, 4);
            setMenuLines("Industry: ", 6);
            setMenuLines("Number of Employees: ", 8);
            setMenuLines("City: ", 10);
            setMenuLines("Country: ", 12);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER]: " + HIGHLIGHT_COLOR, 20);
        }
    }

    // ======================================== 4. LOOKUP OBJECT INFO MENUS ========================================
    // Set the menu String array for lookup of a particular object
    public static void lookupObject(Object object, String... params) {
        // identify which object to lookup and then set the menu appropriately
        if (Lead.class.equals(object.getClass())) {
            Lead lead = (Lead) object;
            setMenuLines("", 1, 7, 9, 11, 13, 14, 15, 16, 17, 18, 19, 21);
            setMenuLines(HIGHLIGHT_COLOR + "Information of Lead with id " + lead.getId() + HIGHLIGHT_COLOR, 4);
            setMenuLines("Name: " + INSERT_HIGHLIGHT_COLOR + lead.getName() + ANSI_RESET, 6);
            setMenuLines("Phone Number: " + INSERT_HIGHLIGHT_COLOR + lead.getPhoneNumber() + ANSI_RESET, 8);
            setMenuLines("Email: " + INSERT_HIGHLIGHT_COLOR + lead.getEmail() + ANSI_RESET, 10);
            setMenuLines("Company Name: " + INSERT_HIGHLIGHT_COLOR + lead.getCompanyName() + ANSI_RESET, 12);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- return to the main menu", 20);

        } else if (Contact.class.equals(object.getClass())) {
            Contact contact = (Contact) object;
            setMenuLines("", 1, 7, 9, 11, 13, 14, 15, 16, 17, 18, 19, 21);
            setMenuLines(HIGHLIGHT_COLOR + "Information of Contact with id " + contact.getId() + HIGHLIGHT_COLOR, 4);
            setMenuLines("Name: " + INSERT_HIGHLIGHT_COLOR + contact.getName() + ANSI_RESET, 6);
            setMenuLines("Phone Number: " + INSERT_HIGHLIGHT_COLOR + contact.getPhoneNumber() + ANSI_RESET, 8);
            setMenuLines("Email: " + INSERT_HIGHLIGHT_COLOR + contact.getEmail() + ANSI_RESET, 10);
            setMenuLines("Company Name: " + INSERT_HIGHLIGHT_COLOR + contact.getCompanyName() + ANSI_RESET, 12);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- return to the main menu", 20);

        } else if (Opportunity.class.equals(object.getClass())) {
            Opportunity opportunity = (Opportunity) object;
            if (params.length == 0) {
                setMenuLines("", 1, 7, 9, 11, 13, 14, 15, 16, 17, 18, 19, 21);
                setMenuLines(HIGHLIGHT_COLOR + "Information of Opportunity with id " + opportunity.getId() + HIGHLIGHT_COLOR, 4);
                setMenuLines("Product: " + INSERT_HIGHLIGHT_COLOR + opportunity.getProduct() + ANSI_RESET, 6);
                setMenuLines("Quantity: " + INSERT_HIGHLIGHT_COLOR + opportunity.getQuantity() + ANSI_RESET, 8);
                setMenuLines("Decision Maker: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getName() + ANSI_RESET, 10);
                setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + opportunity.getStatus() + ANSI_RESET, 12);
                setMenuLines(HIGHLIGHT_COLOR + "ENTER" + ANSI_RESET + "- expand contact | " + HIGHLIGHT_COLOR + "back " + ANSI_RESET + "- return to the main menu", 20);
            } else if (params.length == 1 && params[0].toLowerCase().equals("contact")) {
                setMenuLines("Decision Maker: Id: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getId() + ANSI_RESET, 10);
                setMenuLines("                Name: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getName() + ANSI_RESET, 11);
                setMenuLines("                Phone Number: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getPhoneNumber() + ANSI_RESET, 12);
                setMenuLines("                Email: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getEmail() + ANSI_RESET, 13);
                setMenuLines("                Company Name: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getCompanyName() + ANSI_RESET, 14);
                setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + opportunity.getStatus() + ANSI_RESET, 16);
                setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- return to the main menu", 20);
            }

        } else if (Account.class.equals(object.getClass())) {
            Account account = (Account) object;
            if (params.length == 0) {
                setMenuLines("", 1, 7, 9, 11, 13, 15, 17, 18, 19, 21);
                setMenuLines(HIGHLIGHT_COLOR + "Information of Account with id " + account.getId() + HIGHLIGHT_COLOR, 4);
                setMenuLines("Industry: " + INSERT_HIGHLIGHT_COLOR + account.getIndustry() + ANSI_RESET, 6);
                setMenuLines("Employee Count: " + INSERT_HIGHLIGHT_COLOR + account.getEmployeeCount() + ANSI_RESET, 8);
                setMenuLines("Number of Contacts: " + INSERT_HIGHLIGHT_COLOR + account.getContactList().size() + ANSI_RESET, 10);
                setMenuLines("Number of Opportunities: " + INSERT_HIGHLIGHT_COLOR + account.getOpportunityList().size() + ANSI_RESET, 12);
                setMenuLines("City: " + INSERT_HIGHLIGHT_COLOR + account.getCity() + ANSI_RESET, 14);
                setMenuLines("Country: " + INSERT_HIGHLIGHT_COLOR + account.getCountry() + ANSI_RESET, 16);
                setMenuLines(HIGHLIGHT_COLOR + "contacts " + ANSI_RESET + "- show Account's Contacts | " +
                    HIGHLIGHT_COLOR + "opportunities " + ANSI_RESET + "- show Account's Opportunities | " + HIGHLIGHT_COLOR +
                    "back " + ANSI_RESET + "- return to the main menu", 20);
            } else if (params.length == 1) {
                switch (params[0].toLowerCase()) {
                    case "contacts":
                        break;
                    case "opportunities":
                        break;
                    case "both":
                        break;
                }
            }
        } else {
            throw new IllegalArgumentException("There is no class " + object.getClass());
        }

        PrinterMenu.printMenu("");
    }
    // Set the menu String array to show the provided page (List) of leads
    public static void showLeads(ArrayList<Lead> leads, boolean firstPage, boolean lastPage) {
        setMenuLines("", 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21);
        if (leads.size() == 0) {
            setMenuLines(HIGHLIGHT_COLOR + "There are no Leads" + HIGHLIGHT_COLOR, 4);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "Available Leads" + HIGHLIGHT_COLOR, 4);
        }
        // String is built with the current page of leads and then the corresponding menu String lines are set
        int initialLine = 6;
        StringBuilder leadString = new StringBuilder("");
        for (Lead lead : leads) {
            leadString.append(lead.toString()).append("\n");
        }
        setLinesFromConcatString(leadString.toString(), initialLine);

        if (firstPage && lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- return to the main menu", 20);
        } else if (firstPage) {
            setMenuLines(HIGHLIGHT_COLOR + "next " + ANSI_RESET + "- go to the next page |" + HIGHLIGHT_COLOR +
                " back " + ANSI_RESET + "- return to the main menu", 20);
        } else if (lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + " - return to the main menu", 20);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "next" + ANSI_RESET + "- go to the next page | " + HIGHLIGHT_COLOR +
                "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + "- return to the main menu", 20);
        }
        PrinterMenu.printMenu("");
    }
    // Set the menu String array to show the provided page (List) of opportunities
    public static void showOpportunities(ArrayList<Opportunity> opportunities, boolean firstPage, boolean lastPage, boolean fromAccount) {
        setMenuLines("", 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21);
        if (opportunities.size() == 0) {
            setMenuLines(HIGHLIGHT_COLOR + "There are no Opportunities" + HIGHLIGHT_COLOR, 4);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "Available Opportunities" + HIGHLIGHT_COLOR, 4);
        }
        // String is built with the current page of opportunities and then the corresponding menu String lines are set
        int initialLine = 6;
        StringBuilder opportunityString = new StringBuilder("");
        for (Opportunity opportunity : opportunities) {
            opportunityString.append(opportunity.toString()).append("\n");
        }
        setLinesFromConcatString(opportunityString.toString(), initialLine);
        if (firstPage && lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else if (firstPage) {
            setMenuLines(HIGHLIGHT_COLOR + "next " + ANSI_RESET + "- go to the next page |" + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else if (lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "next" + ANSI_RESET + "- go to the next page |" + HIGHLIGHT_COLOR +
                "previous " + ANSI_RESET + "- return to the previous page |" + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        }
        PrinterMenu.printMenu("");
    }
    // Set the menu String array to show the provided page (List) of contacts
    public static void showContacts(ArrayList<Contact> contacts, boolean firstPage, boolean lastPage, boolean fromAccount) {
        setMenuLines("", 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21);
        if (contacts.size() == 0) {
            setMenuLines(HIGHLIGHT_COLOR + "There are no Contacts" + HIGHLIGHT_COLOR, 4);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "Available Contacts" + HIGHLIGHT_COLOR, 4);
        }
        // String is built with the current page of contacts and then the corresponding menu String lines are set
        int initialLine = 6;
        StringBuilder contactString = new StringBuilder("");
        for (Contact contact : contacts) {
            contactString.append(contact.toString()).append("\n");
        }
        setLinesFromConcatString(contactString.toString(), initialLine);
        if (firstPage && lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else if (firstPage) {
            setMenuLines(HIGHLIGHT_COLOR + "next " + ANSI_RESET + "- go to the next page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else if (lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "next " + ANSI_RESET + "- go to the next page |" + HIGHLIGHT_COLOR +
                "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + (fromAccount ? "- return to Account" : "- return to the main menu"), 20);
        }
        PrinterMenu.printMenu("");
    }
    // Set the menu String array to show the provided page (List) of accounts
    public static void showAccounts(ArrayList<Account> accounts, boolean firstPage, boolean lastPage) {
        setMenuLines("", 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21);
        if (accounts.size() == 0) {
            setMenuLines(HIGHLIGHT_COLOR + "There are no Accounts" + HIGHLIGHT_COLOR, 4);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "Available Accounts" + HIGHLIGHT_COLOR, 4);
        }
        // String is built with the current page of accounts and then the corresponding menu String lines are set
        int initialLine = 6;
        StringBuilder accountString = new StringBuilder("");
        for (Account account : accounts) {
            accountString.append(account.toString()).append("\n");
        }
        setLinesFromConcatString(accountString.toString(), initialLine);
        if (firstPage && lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "ENTER " + ANSI_RESET + "- return to the main menu", 20);
        } else if (firstPage) {
            setMenuLines(HIGHLIGHT_COLOR + "next " + ANSI_RESET + "- go to the next page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + "- return to the main menu", 20);
        } else if (lastPage) {
            setMenuLines(HIGHLIGHT_COLOR + "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + "- return to the main menu", 20);
        } else {
            setMenuLines(HIGHLIGHT_COLOR + "next" + ANSI_RESET + "- go to the next page |" + HIGHLIGHT_COLOR +
                "previous " + ANSI_RESET + "- return to the previous page | " + HIGHLIGHT_COLOR +
                "back " + ANSI_RESET + "- return to the main menu", 20);
        }
        PrinterMenu.printMenu("");
    }

    // Set menu lines from a concatenated String starting from the index passed in
    public static void setLinesFromConcatString(String str, int index){
        String[] stringArr = str.split("\n");
        for (String singleString : stringArr){
            String[] dividedString = divideText(singleString);
            setMenuLines(dividedString[0], index++);
            while (!dividedString[1].equals("")){
                dividedString = divideText(dividedString[1]);
                setMenuLines(dividedString[0], index++);
            }
        }
    }

    // Set the warning
    public static void setWarning(String message) {
        setMenuLines(ANSI_BRIGHT_RED + message + ANSI_RESET, ERROR_LINE);
    }
    public static String getWarning() { return getMenuLine(ERROR_LINE).replace(ANSI_BRIGHT_RED,"").replace(ANSI_RESET,""); }
    public static void clearWarning() {
        setMenuLines("",ERROR_LINE);
    }
}
