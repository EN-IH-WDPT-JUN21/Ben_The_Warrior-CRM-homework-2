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
    // String array to print the menu
    private static String[] menuLine = {"","","","","","","","","","","","","","","","","","","","","","",""};

    // Set the menu String array for the main menu
    private static void mainMenuLines(){
        setMenuLines("",0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22);
        setMenuLines("Welcome to Ben's CMR program.",1);
        setMenuLines("Enter " + HIGHLIGHT_COLOR + "help" + ANSI_RESET + " for a list of valid commands!",20);
        setMenuLines("Enter " + HIGHLIGHT_COLOR + "exit" + ANSI_RESET + " to close the application!",21);
    }

    // Set the menu String array for the help menu with a list of available commands
    private static void helpMenuLines(){
        setMenuLines("",0,2,3,5,7,9,11,13,15,16,18,20,22);
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

    // Set one or multiple lines of the menu String array to the String passed in
    public static void setMenuLines(String str, int... lines){
        for (int line : lines) {
            if (line < getProgramHeight()){
                menuLine[line] = str;
            }
        }
    }

    // getter for a particular line in the menu String array
    public static String getMenuLine(int ind){
        if (ind < getProgramHeight()){
            return menuLine[ind];
        }
        throw new IllegalArgumentException("Index is greater than the applications height!");
    }


    // ======================================== 2. CREATE MAIN MENU ========================================
    // Sets appropriate menu String array and prints it
    public static void printMenu(String menuChoice, String... params) {
        switch (menuChoice){
            case "main":
                mainMenuLines();
                break;
            case "help":
                helpMenuLines();
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
        for (String line : menuLine){
            print(line);
        }
        printFull();
    }

    // ======================================== 3.1 NEW LEAD MENU ========================================
    private static void newLeadLines(String... params) {
        // Set initial menu for the lead creation
        if (params.length == 0){
            setMenuLines("",0,1,2,3,5,7,9,11,13,14,15,16,17,18,19,21,22);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Lead" + HIGHLIGHT_COLOR,4);
            setMenuLines("Name: ",6);
            setMenuLines("Phone Number: ",8);
            setMenuLines("Email: ",10);
            setMenuLines("Company Name: ",12);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Name: " + HIGHLIGHT_COLOR,20);
        }else if (params.length == 2){
            // Update the menu for the lead creation
            switch (params[0].toLowerCase()){
                case "name":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Phone Number: " + HIGHLIGHT_COLOR,20);
                    break;
                case "phone":
                    setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,8);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Email: " + HIGHLIGHT_COLOR,20);
                    break;
                case "email":
                    setMenuLines(getMenuLine(10) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,10);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Lead Company Name: " + HIGHLIGHT_COLOR,20);
                    break;
                case "company":
                    setMenuLines(getMenuLine(12) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,12);
                    setMenuLines("Other commands:  " + HIGHLIGHT_COLOR + "back" + ANSI_RESET,17);
                    setMenuLines(HIGHLIGHT_COLOR + "ENTER - Confirm New Lead" + HIGHLIGHT_COLOR,20);
                    break;
            }
        }else{
            throw new IllegalArgumentException("Incorrect number of parameters");
        }
    }

    // ======================================== 3.2 CONVERT LEAD MENU ========================================

    private static void convertLeadLines(String... params){
        // Set New Opportunity menu
        if (params.length == 0){
            setMenuLines("",0,1,2,3,5,7,9,10,11,12,13,14,15,16,17,18,19,21,22);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Opportunity" + HIGHLIGHT_COLOR,4);
            setMenuLines("Product: ",6);
            setMenuLines("Quantity: ",8);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Product type [HYBRID, FLATBED or BOX]: " + HIGHLIGHT_COLOR,20);
        // Update the convert lead menu
        }else if(params.length == 2){
            switch (params[0].toLowerCase()){
                case "product":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Quantity: " + HIGHLIGHT_COLOR,20);
                    break;
                case "industry":
                    setMenuLines(getMenuLine(6) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,6);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Employee Count: " + HIGHLIGHT_COLOR,20);
                    break;
                case "employees":
                    setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,8);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert City: " + HIGHLIGHT_COLOR,20);
                    break;
                case "city":
                    setMenuLines(getMenuLine(10) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,10);
                    setMenuLines(HIGHLIGHT_COLOR + "Insert Country: " + HIGHLIGHT_COLOR,20);
                    break;
                case "country":
                    setMenuLines(getMenuLine(12) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,12);
                    setMenuLines("Other commands:  " + HIGHLIGHT_COLOR + "back" + ANSI_RESET,17);
                    setMenuLines(HIGHLIGHT_COLOR + "ENTER - Confirm New Account" + HIGHLIGHT_COLOR,20);
                    break;
            }
        }else if(params.length == 3 && params[0].toLowerCase().equals("quantity and contact")){
            setMenuLines(getMenuLine(8) + INSERT_HIGHLIGHT_COLOR + params[1] + ANSI_RESET,8);
            setMenuLines("Contact Name: " + INSERT_HIGHLIGHT_COLOR + params[2] + ANSI_RESET,10);
            setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + Status.OPEN + ANSI_RESET,12);
            setMenuLines("Other commands:  " + HIGHLIGHT_COLOR + "back" + ANSI_RESET,17);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER - Confirm Opportunity Information" + HIGHLIGHT_COLOR,20);
        //Set New Account menu
        }else if (params.length == 1 && params[0].toLowerCase().equals("account")){
            setMenuLines("",0,1,2,3,5,7,9,11,13,14,15,16,17,18,19,21,22);
            setMenuLines(HIGHLIGHT_COLOR + "Create New Account" + HIGHLIGHT_COLOR,4);
            setMenuLines("Industry: ",6);
            setMenuLines("Number of Employees: ",8);
            setMenuLines("City: ",10);
            setMenuLines("Country: ",12);
            setMenuLines(HIGHLIGHT_COLOR + "Insert Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER]: " + HIGHLIGHT_COLOR,20);
        }
    }

    // ======================================== 4. LOOKUP OBJECT INFO MENUS ========================================
    public static void lookupObject(Object object, String... params){
        if (Lead.class.equals(object.getClass())) {
            Lead lead = (Lead) object;
            setMenuLines("",1,2,3,5,7,9,11,13,14,15,16,17,18,19,21,22);
            setMenuLines(HIGHLIGHT_COLOR + "Information of Lead with id " + lead.getId() + HIGHLIGHT_COLOR,4);
            setMenuLines("Name: " + INSERT_HIGHLIGHT_COLOR + lead.getName() + ANSI_RESET, 6);
            setMenuLines("Phone Number: " + INSERT_HIGHLIGHT_COLOR + lead.getPhoneNumber() + ANSI_RESET, 8);
            setMenuLines("Email: " + INSERT_HIGHLIGHT_COLOR + lead.getEmail() + ANSI_RESET, 10);
            setMenuLines("Company Name: " + INSERT_HIGHLIGHT_COLOR + lead.getCompanyName() + ANSI_RESET, 12);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER - Go back to the main menu" + HIGHLIGHT_COLOR,20);

        }else if (Contact.class.equals(object.getClass())){
            Contact contact = (Contact) object;
            setMenuLines("",1,2,3,5,7,9,11,13,14,15,16,17,18,19,21,22);
            setMenuLines(HIGHLIGHT_COLOR + "Information of Contact with id " + contact.getId() + HIGHLIGHT_COLOR,4);
            setMenuLines("Name: " + INSERT_HIGHLIGHT_COLOR + contact.getName() + ANSI_RESET, 6);
            setMenuLines("Phone Number: " + INSERT_HIGHLIGHT_COLOR + contact.getPhoneNumber() + ANSI_RESET, 8);
            setMenuLines("Email: " + INSERT_HIGHLIGHT_COLOR + contact.getEmail() + ANSI_RESET, 10);
            setMenuLines("Company Name: " + INSERT_HIGHLIGHT_COLOR + contact.getCompanyName() + ANSI_RESET, 12);
            setMenuLines(HIGHLIGHT_COLOR + "ENTER - Go back to the main menu" + HIGHLIGHT_COLOR,20);

        }else if (Opportunity.class.equals(object.getClass())){
            Opportunity opportunity = (Opportunity) object;
            if (params.length == 0){
                setMenuLines("",1,2,3,5,7,9,11,13,14,15,16,17,18,19,21,22);
                setMenuLines(HIGHLIGHT_COLOR + "Information of Opportunity with id " + opportunity.getId() + HIGHLIGHT_COLOR,4);
                setMenuLines("Product: " + INSERT_HIGHLIGHT_COLOR + opportunity.getProduct() + ANSI_RESET, 6);
                setMenuLines("Quantity: " + INSERT_HIGHLIGHT_COLOR + opportunity.getQuantity() + ANSI_RESET, 8);
                setMenuLines("Decision Maker: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getName() + ANSI_RESET, 10);
                setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + opportunity.getStatus() + ANSI_RESET, 12);
                setMenuLines(HIGHLIGHT_COLOR + "contact" + ANSI_RESET + "to expand contact or " + HIGHLIGHT_COLOR + "back" + ANSI_RESET + "to get back to the main menu",20);
            }else if (params.length == 1 && params[0].toLowerCase().equals("contact")){
                setMenuLines("Decision Maker: Id: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getId() + ANSI_RESET, 10);
                setMenuLines("                Name: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getName() + ANSI_RESET, 11);
                setMenuLines("                Phone Number: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getPhoneNumber() + ANSI_RESET, 12);
                setMenuLines("                Email: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getEmail() + ANSI_RESET, 13);
                setMenuLines("                Company Name: " + INSERT_HIGHLIGHT_COLOR + opportunity.getDecisionMaker().getCompanyName() + ANSI_RESET, 14);
                setMenuLines("Status: " + INSERT_HIGHLIGHT_COLOR + opportunity.getStatus() + ANSI_RESET, 16);
                setMenuLines(HIGHLIGHT_COLOR + "ENTER - Go back to the main menu" + HIGHLIGHT_COLOR,20);
            }

        }else if (Account.class.equals(object.getClass())){
            Account account = (Account) object;
            if (params.length == 0){
                setMenuLines(HIGHLIGHT_COLOR + "Information of Account with id " + account.getId() + HIGHLIGHT_COLOR,4);
                setMenuLines("Industry: " + INSERT_HIGHLIGHT_COLOR + account.getIndustry() + ANSI_RESET, 6);
                setMenuLines("Employee Count: " + INSERT_HIGHLIGHT_COLOR + account.getEmployeeCount() + ANSI_RESET, 8);
                setMenuLines("Number of Contacts: " + INSERT_HIGHLIGHT_COLOR + account.getContactList().size() + ANSI_RESET, 10);
                setMenuLines("Number of Opportunities: " + INSERT_HIGHLIGHT_COLOR + account.getOpportunityList().size() + ANSI_RESET, 12);
                setMenuLines("City: " + INSERT_HIGHLIGHT_COLOR + account.getCity() + ANSI_RESET, 14);
                setMenuLines("Country: " + INSERT_HIGHLIGHT_COLOR + account.getCountry() + ANSI_RESET, 16);
            }
            else if (params.length == 1){
                switch (params[0].toLowerCase()){
                    case "contacts":
                        break;
                    case "opportunities":
                        break;
                    case "both":
                        break;
                }
            }
        }else{
            warningMessage("There is no class " + object.getClass());
            return;
        }

        PrinterMenu.printMenu("");
    }
    public static void printAccountInfo(Account account) {
        Printer.clearCommandLine();
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
        Printer.clearCommandLine();
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
        Printer.clearCommandLine();
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
        Printer.clearCommandLine();
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
