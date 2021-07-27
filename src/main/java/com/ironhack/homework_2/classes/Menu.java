package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import com.ironhack.homework_2.utils.JsonDatabaseUtility;
import com.ironhack.homework_2.utils.Printer;
import com.ironhack.homework_2.utils.PrinterMenu;

import java.util.Scanner;

import static com.ironhack.homework_2.utils.Utils.*;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final JsonDatabaseUtility db = new JsonDatabaseUtility();
    // Variable to check if the user asked for the available commands
    private static boolean showHelp;

    public static void mainMenu() {
        String input;
        boolean running = true;
        showHelp = false;
        while (running) {
            // if the user asked for available commands print help menu, otherwise print main menu
            if (showHelp){
                PrinterMenu.printMenu("help");
                showHelp = false;
            }else{
                PrinterMenu.printMenu("main");
            }
            // get a user input, if it is valid compute the command otherwise print a warning message
            input = scanner.nextLine();
            if (isValidCommand(input)) {
                running = computeCommand(input);
            } else {
                Printer.warningMessage("There is no such command as \"" + input + "\"! To see the list of available commands type help");
            }
        }
    }

    public static boolean computeCommand(String input) {
        String[] inputArray = input.trim().toLowerCase().split(" ");
        switch (inputArray[0]) {
            case "new":
                if (inputArray[1].equals("lead")) {
                    promptLead();
                }
                break;
            case "show":
                switch (inputArray[1]){
                    case "leads":
                        db.showLeads();
                        break;
                    case "opportunities":
                        db.showOpportunities();
                        break;
                    case "contacts":
                        db.showContacts();
                        break;
                    case "accounts":
                        db.showAccounts();
                        break;
                }
                break;
            case "lookup":
                switch (inputArray[1]){
                    case "lead":
                        Lead lead = db.lookupLeadId(Integer.parseInt(inputArray[2]));
                        PrinterMenu.lookupObject(lead);
                        promptDecision("enter");
                        break;
                    case "opportunity":
                        Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[2]));
                        PrinterMenu.lookupObject(opportunity);

                        break;
                    case "contact":
                        Contact contact = db.lookupContactId(Integer.parseInt(inputArray[2]));
                        PrinterMenu.lookupObject(contact);
                        promptDecision("enter");
                        break;
                    case "account":
                        Account account = db.lookupAccountId(Integer.parseInt(inputArray[2]));
                        PrinterMenu.lookupObject(account);
                        break;
                    default:
                        break;
                }
                break;
            case "convert":
                promptConvert(Integer.parseInt(inputArray[1]));
                break;
            case "close_won":
            case "close_lost":
                db.updateStatus(Status.valueOf(inputArray[0].toUpperCase()), Integer.parseInt(inputArray[1]));
                break;
            case "help":
                Menu.showHelp = true;
                break;
            case "save":

                break;
            case "exit":
                return false;
            default:
                break;
        }
        return true;
    }

    private static void promptConvert(int id) {
        if (db.hasLead(id)){
            String contactName = db.getLeadHash().get(id).getName();
            // prompt Opportunity
            PrinterMenu.printMenu("convert");
            Product product = promptProduct();
            PrinterMenu.printMenu("convert", "product", product.toString());
            int quantity = promptNumber();

            //prompt Account
            PrinterMenu.printMenu("convert","quantity and contact",Integer.toString(quantity), contactName);
            if (!promptDecision("enter back")) {
                return;
            }
            PrinterMenu.printMenu("convert","account");
            Industry industry = promptIndustry();
            PrinterMenu.printMenu("convert", "industry", industry.toString());
            int employeeCount = promptNumber();
            PrinterMenu.printMenu("convert", "employees", Integer.toString(employeeCount));
            String city = promptString("");
            PrinterMenu.printMenu("convert", "city", city);
            String country = promptString("");
            PrinterMenu.printMenu("convert", "country", country);
            if (promptDecision("enter back")){
                db.convertLead(id, product, quantity, industry, employeeCount, city, country);
            }
        }else{
            Printer.warningMessage("There is no lead with id " + id + " to convert!");
        }
    }

    private static void promptLead() {
        // name, phoneNumber, email, and companyName
        PrinterMenu.printMenu("lead");
        String name = promptString("name");
        PrinterMenu.printMenu("lead", "name", name);
        String phoneNumber = promptString("phone");
        PrinterMenu.printMenu("lead","phone", phoneNumber);
        String email = promptString("email");
        PrinterMenu.printMenu("lead","email", email);
        String companyName = promptString("");
        PrinterMenu.printMenu("lead","company",companyName);
        if (promptDecision("enter back")) {
            db.addLead(name, phoneNumber, email, companyName);
        }
    }

    private static boolean promptDecision(String decision) {
        String input;
        switch (decision){
            case "enter back":
                do {
                    input = scanner.nextLine().trim().toLowerCase();
                    switch (input) {
                        case "":
                            return true;
                        case "back":
                            return false;
                    }
                } while (true);
            case "enter":
                scanner.nextLine();
                return true;
        }
        return false;
    }

    private static Product promptProduct() {
        String input;
        do {
            input = scanner.nextLine().trim().toUpperCase();
        } while (!validProduct(input));
        return Product.valueOf(input);
    }

    private static Industry promptIndustry() {
        String input;
        do {
            input = scanner.nextLine().trim().toUpperCase();
        } while (!validIndustry(input));
        return Industry.valueOf(input);
    }

    private static int promptNumber() {
        String input;
        do {
            input = scanner.nextLine().trim();
        } while (!validNumber(input));
        return Integer.parseInt(input);
    }

    private static String promptString(String checkCondition) {
        String input;
        switch (checkCondition) {
            case "phoneNumber":
                do {
                    input = scanner.nextLine().trim();
                } while (!validPhone(input));
                return input;
            case "email":
                do {
                    input = scanner.nextLine().trim();
                } while (!validEmail(input));
                return input;
            case "name":
                do {
                    input = scanner.nextLine().trim();
                } while (!validName(input));
                return input;
            default:
                do {
                    input = scanner.nextLine().trim();
                } while (!validString(input));
                return input;
        }
    }
}
