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
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

public class PrinterMenu extends Printer {
    private static final String ANSI_RESET = getAnsiReset();
    private static final String HIGHLIGHT_COLOR = getHighlightColor();
    private static final String INSERT_HIGHLIGHT_COLOR = getInsertHighlightColor();


    // ======================================== 2. CREATE MAIN MENU ========================================
    public static void printMainMenu() {
        printProgramTitle();
        print();
        print("Welcome to Ben's CMR program. Here are the main commands:");
        print();
        print();
        print(HIGHLIGHT_COLOR + "create lead" + ANSI_RESET + " - Creates a new Lead");
        print();
        print(HIGHLIGHT_COLOR + "convert <ID>" + ANSI_RESET + " - Converts a Lead into an Opportunity");
        print();
        print(HIGHLIGHT_COLOR + "close won <ID>" + ANSI_RESET + " - Close Won Opportunity");
        print();
        print(HIGHLIGHT_COLOR + "close lost <ID>" + ANSI_RESET + " - Close Lost Opportunity");
        print();
        print(HIGHLIGHT_COLOR + "lookup <OBJECT> <ID>" + ANSI_RESET + " - Search for specific Lead, Opportunity, Account or Contact");
        print();
        print(HIGHLIGHT_COLOR + "show <OBJECT PLURAL>" + ANSI_RESET + " - List all Leads, Opportunities, Accounts or Contacts");
        print();
        print();
        print(HIGHLIGHT_COLOR + "help h" + ANSI_RESET + " - Explains usage of available commands");
        print();
        print(HIGHLIGHT_COLOR + "save s" + ANSI_RESET + " - Saves the changed data");
        print();
        print(HIGHLIGHT_COLOR + "exit x" + ANSI_RESET + " - Saves and exits  the program");
        print();
        printFull();
    }

    // ======================================== 3.1 CREATE LEAD MENU ========================================
    public static void printCreateLeadMenu() {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        print();
        print("Name: ");
        print();
        print("Email: ");
        print();
        print("Phone Number: ");
        print();
        print("Company Name: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Lead Name: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateLeadMenu(String name) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Lead" + ANSI_RESET);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        print();
        print("Email: ");
        print();
        print("Phone Number: ");
        print();
        print("Company Name: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Lead Email: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateLeadMenu(String name, String email) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        print();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        print();
        print("Phone Number: ");
        print();
        print("Company Name: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Lead Phone Number: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateLeadMenu(String name, String email, String phoneNr) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        print();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        print();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + phoneNr + ANSI_RESET);
        print();
        print("Company Name: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Lead Company Name: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateLeadMenu(String name, String email, String phoneNr, String companyName) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + name + ANSI_RESET);
        print();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + email + ANSI_RESET);
        print();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + phoneNr + ANSI_RESET);
        print();
        print("Company Name: " + INSERT_HIGHLIGHT_COLOR + companyName + ANSI_RESET);
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Lead" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }


    // ======================================== 3.2 CREATE OPPORTUNITY MENU ========================================
    public static void printCreateOpportunityMenu() {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        print();
        print("Product: ");
        print();
        print("Quantity: ");
        print();
        print();
        print();
        print();
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Product type [HYBRID, FLATBED or BOX]: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateOpportunityMenu(Product product) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        print();
        print("Product: " + INSERT_HIGHLIGHT_COLOR + product + ANSI_RESET);
        print();
        print("Quantity: ");
        print();
        print();
        print();
        print();
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Quantity: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateOpportunityMenu(Product product, int quantity, String contactName) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR);
        print();
        print("Product: " + INSERT_HIGHLIGHT_COLOR + product + ANSI_RESET);
        print();
        print("Quantity: " + INSERT_HIGHLIGHT_COLOR + quantity + ANSI_RESET);
        print();
        print("Contact Name: " + INSERT_HIGHLIGHT_COLOR + contactName + ANSI_RESET); // it can be the lead name if complicated we can delete
        print();
        print("Status: " + INSERT_HIGHLIGHT_COLOR + Status.OPEN + ANSI_RESET);
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Opportunity" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }


    // ======================================== 3.3 CREATE ACCOUNT MENU ========================================
    public static void printCreateAccountMenu() {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        print();
        print("Industry: ");
        print();
        print("Number of Employees: ");
        print();
        print("City: ");
        print();
        print("Country: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER]: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateAccountMenu(Industry industry) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        print();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        print();
        print("Number of Employees: ");
        print();
        print("City: ");
        print();
        print("Country: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Number of Employees: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        print();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        print();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        print();
        print("City: ");
        print();
        print("Country: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert City: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount, String city) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        print();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        print();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        print();
        print("City: " + INSERT_HIGHLIGHT_COLOR + city + ANSI_RESET);
        print();
        print("Country: ");
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Country: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printCreateAccountMenu(Industry industry, int employeeCount, String city, String country) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR);
        print();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + industry + ANSI_RESET);
        print();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + employeeCount + ANSI_RESET);
        print();
        print("City: " + INSERT_HIGHLIGHT_COLOR + city + ANSI_RESET);
        print();
        print("Country: " + INSERT_HIGHLIGHT_COLOR + country + ANSI_RESET);
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Confirm New Account" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }


    // ======================================== 4. LOOKUP OBJECT INFO MENUS ========================================
    public static void printAccountInfo(Account account) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Account ID - " + account.getId() + HIGHLIGHT_COLOR);
        print();
        print("Industry: " + INSERT_HIGHLIGHT_COLOR + account.getIndustry() + ANSI_RESET);
        print();
        print("Number of Employees: " + INSERT_HIGHLIGHT_COLOR + account.getEmployeeCount() + ANSI_RESET);
        print();
        print("City: " + INSERT_HIGHLIGHT_COLOR + account.getCity() + ANSI_RESET);
        print();
        print("Country: " + INSERT_HIGHLIGHT_COLOR + account.getCountry() + ANSI_RESET);
        print();
        print("Contacts: " + INSERT_HIGHLIGHT_COLOR + account.getContactList() + ANSI_RESET);
        print();
        print("Opportunities: " + INSERT_HIGHLIGHT_COLOR + account.getOpportunityList() + ANSI_RESET);
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Go Back" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printOpportunityInfo(Opportunity opportunity) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Opportunity ID - " + opportunity.getId() + HIGHLIGHT_COLOR);
        print();
        print("Product: " + INSERT_HIGHLIGHT_COLOR + opportunity.getProduct() + ANSI_RESET);
        print();
        print("Quantity: " + INSERT_HIGHLIGHT_COLOR + opportunity.getQuantity() + ANSI_RESET);
        print();
        print("Contact Name: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker() + ANSI_RESET); // it can be the lead name if complicated we can delete
        print();
        print("Status: " + INSERT_HIGHLIGHT_COLOR + opportunity.getStatus() + ANSI_RESET);
        print();
        print();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Go Back" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printContactInfo(Contact contact) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Contact ID - " + contact.getId() + HIGHLIGHT_COLOR);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + contact.getName() + ANSI_RESET);
        print();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + contact.getEmail() + ANSI_RESET);
        print();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + contact.getPhoneNumber() + ANSI_RESET); // it can be the lead name if complicated we can delete
        print();
        print("Company Name: " + INSERT_HIGHLIGHT_COLOR + contact.getCompanyName() + ANSI_RESET);
        print();
        print();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "ENTER - Go Back" + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

    public static void printLeadInfo(Lead lead) {
        printProgramTitle();
        print();
        print();
        print();
        print();
        print(HIGHLIGHT_COLOR + "Lead ID - " + lead.getId() + HIGHLIGHT_COLOR);
        print();
        print("Name: " + INSERT_HIGHLIGHT_COLOR + lead.getName() + ANSI_RESET);
        print();
        print("Email: " + INSERT_HIGHLIGHT_COLOR + lead.getEmail() + ANSI_RESET);
        print();
        print("Phone Number: " + INSERT_HIGHLIGHT_COLOR + lead.getPhoneNumber() + ANSI_RESET);
        print();
        print("Company Name: " + INSERT_HIGHLIGHT_COLOR + lead.getCompanyName() + ANSI_RESET);
        print();
        print();
        print();
        print();
        print("Other commands:  " + HIGHLIGHT_COLOR + "/help  /back" + ANSI_RESET);
        print();
        print();
        print(HIGHLIGHT_COLOR + "Insert Lead Company Name: " + HIGHLIGHT_COLOR);
        print();
        print();
        printFull();
    }

}
