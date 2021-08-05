package com.ironhack.homework_2.main;

import com.ironhack.homework_2.classes.Account;
import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import com.ironhack.homework_2.utils.JsonDatabaseUtility;
import com.ironhack.homework_2.utils.PrinterMenu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.ironhack.homework_2.utils.Utils.*;

public class Menu {
    private final Scanner scanner;
    private final JsonDatabaseUtility db;

    // Variable to check if the user asked for the available commands
    private boolean showHelp;

    public Menu(){
        scanner = new Scanner(System.in);
        db  = new JsonDatabaseUtility();
        try {
            db.load();
        } catch (IOException e) {
            PrinterMenu.setWarning(e.getMessage());
        }
        setShowHelp(false);
    }

    public Menu(InputStream inputStream){
        scanner = new Scanner(inputStream);
        db  = new JsonDatabaseUtility("dummy");
        try {
            db.load();
        } catch (IOException e) {
            PrinterMenu.setWarning(e.getMessage());
        }
        setShowHelp(false);
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public void setShowHelp(boolean showHelp) {
        this.showHelp = showHelp;
    }

    public JsonDatabaseUtility getDatabase(){
        return this.db;
    }

    // Core method of the application. This method is running while the app is running and only returns when closing the app
    public void mainMenu() {
        String input;
        boolean running = true;
        showHelp = false;
        while (running) {
            // if the user asked for available commands print help menu, otherwise print main menu
            if (isShowHelp()){
                PrinterMenu.printMenu("help");
                setShowHelp(false);
            } else {
                PrinterMenu.printMenu("main");
            }
            // get a user input, if it is valid compute the command otherwise print a warning message
            input = scanner.nextLine();
            if (isValidCommand(input)) {
                PrinterMenu.clearWarning();
                running = computeCommand(input);
            } else {
                PrinterMenu.setWarning("There is no such command as \"" + input + "\"! To see the list of available commands type help!");
            }
        }
    }

    // Method to compute commands after being validated
    public boolean computeCommand(String input) {
        String[] inputArray = input.trim().toLowerCase().split(" ");
        // commands are computed word by word and the appropriate method is called
        switch (inputArray[0]) {
            case "new":
                if (inputArray[1].equals("lead")) {
                    promptLead();
                }
                break;
            case "show":
                switch (inputArray[1]) {
                    case "leads":
                        showLeadsMenu();
                        break;
                    case "opportunities":
                        showOpportunitiesMenu();
                        break;
                    case "contacts":
                        showContactsMenu();
                        break;
                    case "accounts":
                        showAccountsMenu();
                        break;
                }
                break;
            case "lookup":
                switch (inputArray[1]) {
                    case "lead":
                        try {
                            // If there is no lead an error is thrown
                            // Otherwise the command can be correctly computed and the warning messages can be cleared
                            Lead lead = db.lookupLeadId(Integer.parseInt(inputArray[2]));
                            PrinterMenu.clearWarning();
                            PrinterMenu.lookupObject(lead);
                            promptDecision("enter");
                        } catch (IllegalArgumentException e) {
                            PrinterMenu.setWarning(e.getMessage());
                        }
                        break;
                    case "opportunity":
                        try {
                            // If there is no opportunity an error is thrown
                            // Otherwise the command can be correctly computed and the warning messages can be cleared
                            Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[2]));
                            PrinterMenu.clearWarning();
                            PrinterMenu.lookupObject(opportunity);
                            boolean decision = promptDecision("enter back");
                            if (decision) {
                                PrinterMenu.lookupObject(opportunity, "contact");
                                promptDecision("enter");
                            }
                        } catch (IllegalArgumentException e) {
                            PrinterMenu.setWarning(e.getMessage());
                        }

                        break;
                    case "contact":
                        try {
                            // If there is no contact an error is thrown
                            // Otherwise the command can be correctly computed and the warning messages can be cleared
                            Contact contact = db.lookupContactId(Integer.parseInt(inputArray[2]));
                            PrinterMenu.clearWarning();
                            PrinterMenu.lookupObject(contact);
                            promptDecision("enter");
                        } catch (IllegalArgumentException e) {
                            PrinterMenu.setWarning(e.getMessage());
                        }
                        break;
                    case "account":
                        try {
                            // If there is no account an error is thrown
                            // Otherwise the command can be correctly computed and the warning messages can be cleared
                            Account account = db.lookupAccountId(Integer.parseInt(inputArray[2]));
                            PrinterMenu.clearWarning();
                            lookupAccountMenu(account);
                        } catch (IllegalArgumentException e) {
                            PrinterMenu.setWarning(e.getMessage());
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "convert":
                promptConvert(Integer.parseInt(inputArray[1]));
                break;
            case "close-won":
                try {
                    // If there is no opportunity an error is thrown
                    // Otherwise the command can be correctly computed and the warning messages can be cleared
                    Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[1]));
                    opportunity.setStatus(Status.CLOSED_WON);
                } catch (IllegalArgumentException e) {
                    PrinterMenu.setWarning(e.getMessage());
                }
                break;
            case "close-lost":
                try {
                    // If there is no opportunity an error is thrown
                    // Otherwise the command can be correctly computed and the warning messages can be cleared
                    Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[1]));
                    opportunity.setStatus(Status.CLOSED_LOST);
                } catch (IllegalArgumentException e) {
                    PrinterMenu.setWarning(e.getMessage());
                }
                break;
                // show help menu with all available commands
            case "help":
                setShowHelp(true);
                break;
                // sava database into json file
            case "save":
                try{
                    db.save();
                }catch (IOException e){
                    PrinterMenu.setWarning("An error as occurred. Database was not successfully saved!");
                }
                break;
            case "exit":
                PrinterMenu.printMenu("exit");
                if (promptDecision("exit")){
                    try{
                        db.save();
                    }catch (IOException e){
                        PrinterMenu.setWarning("An error as occurred. Database was not successfully saved!");
                    }
                }
                return false;
            default:
                break;
        }
        return true;
    }

    // Method to create the menu when looking up an account
    private void lookupAccountMenu(Account account){
        PrinterMenu.lookupObject(account);
        // Allow user to see list of contacts and opportunities n the looked up account
        while (true) {
            int answer = promptMultipleDecisions("contacts", "opportunities", "back");
            switch (answer) {
                case 0:
                    showContactsMenu(account.getContactList());
                    PrinterMenu.lookupObject(account);
                    break;
                case 1:
                    showOpportunitiesMenu(account.getOpportunityList());
                    PrinterMenu.lookupObject(account);
                    break;
                case 2:
                    return;
            }
        }
    }

    // Method to create the menu showing all available leads
    private void showLeadsMenu() {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;

        // TreeMap with all leads is created to sort them by the keys
        TreeMap<Integer, Lead> leadTreeMap = new TreeMap<>(db.getLeadHash());
        //TreeMap is converted in a List of Lists of Leads where each outer List corresponds to one page
        List<ArrayList<Lead>> listListLead = new ArrayList<>();
        listListLead.add(new ArrayList<>());
        Set<Map.Entry<Integer, Lead>> entryLeadSet = leadTreeMap.entrySet();
        for (Map.Entry<Integer, Lead> entry : entryLeadSet) {
            if (currentIndex++ < maxElements) {
                listListLead.get(currentPage).add(entry.getValue());
            } else {
                listListLead.add(new ArrayList<>());
                listListLead.get(++currentPage).add(entry.getValue());
            }
        }

        // Allow user to change between the pages
        currentPage = 0;
        int numPages = listListLead.size();
        int decision;
        while (true) {
            PrinterMenu.showLeads(listListLead.get(currentPage), currentPage == 0, currentPage + 1 == numPages);
            if (listListLead.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }

    // Method to create the menu showing all available Opportunities
    private void showOpportunitiesMenu(){
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        // TreeMap with all opportunities is created to sort them by the keys
        TreeMap<Integer, Opportunity> opportunityTreeMap = new TreeMap<>(db.getOpportunityHash());
        //TreeMap is converted in a List of Lists of Opportunities where each outer List corresponds to one page
        List<ArrayList<Opportunity>> listListOpportunity = new ArrayList<>();
        listListOpportunity.add(new ArrayList<>());
        Set<Map.Entry<Integer, Opportunity>> entryOpportunitySet = opportunityTreeMap.entrySet();
        for (Map.Entry<Integer, Opportunity> entry : entryOpportunitySet) {
            if (currentIndex++ < maxElements) {
                listListOpportunity.get(currentPage).add(entry.getValue());
            } else {
                listListOpportunity.add(new ArrayList<>());
                listListOpportunity.get(++currentPage).add(entry.getValue());
            }
        }
        // Allow user to change between the pages
        currentPage = 0;
        int numPages = listListOpportunity.size();
        int decision;
        while (true) {
            PrinterMenu.showOpportunities(listListOpportunity.get(currentPage), currentPage == 0, currentPage + 1 == numPages, false);
            if (listListOpportunity.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }
    // Method to create the menu showing all available Accounts
    private void showAccountsMenu(){
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        // TreeMap with all accounts is created to sort them by the keys
        TreeMap<Integer, Account> accountTreeMap = new TreeMap<>(db.getAccountHash());
        //TreeMap is converted in a List of Lists of Accounts where each outer List corresponds to one page
        List<ArrayList<Account>> listListAccount = new ArrayList<>();
        listListAccount.add(new ArrayList<>());
        Set<Map.Entry<Integer, Account>> entryAccountSet = accountTreeMap.entrySet();

        for (Map.Entry<Integer, Account> entry : entryAccountSet) {
            if (currentIndex++ < maxElements) {
                listListAccount.get(currentPage).add(entry.getValue());
            } else {
                listListAccount.add(new ArrayList<>());
                listListAccount.get(++currentPage).add(entry.getValue());
            }
        }
        // Allow user to change between the pages
        currentPage = 0;
        int numPages = listListAccount.size();
        int decision;
        while (true) {
            PrinterMenu.showAccounts(listListAccount.get(currentPage), currentPage == 0, currentPage + 1 == numPages);
            if (listListAccount.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }
    // Method to create the menu showing all available Contacts
    private void showContactsMenu(){
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        // TreeMap with all contacts is created to sort them by the keys
        TreeMap<Integer, Contact> contactTreeMap = new TreeMap<>(db.getContactHash());
        //TreeMap is converted in a List of Lists of Contacts where each outer List corresponds to one page
        List<ArrayList<Contact>> listListContact = new ArrayList<>();
        listListContact.add(new ArrayList<>());
        Set<Map.Entry<Integer, Contact>> entryContactSet = contactTreeMap.entrySet();

        for (Map.Entry<Integer, Contact> entry : entryContactSet) {
            if (currentIndex++ < maxElements) {
                listListContact.get(currentPage).add(entry.getValue());
            } else {
                listListContact.add(new ArrayList<>());
                listListContact.get(++currentPage).add(entry.getValue());
            }
        }
        // Allow user to change between the pages
        currentPage = 0;
        int numPages = listListContact.size();
        int decision;
        while (true) {
            PrinterMenu.showContacts(listListContact.get(currentPage), currentPage == 0, currentPage + 1 == numPages, false);
            if (listListContact.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }
    // Method to create the menu showing all available Contacts in a List
    private void showContactsMenu(ArrayList<Contact> contactList) {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentPage = 0;
        int currentIndex = 0;
        int decision;
        // List of contacts is separated in multiple lists (pages)
        List<ArrayList<Contact>> listListContacts = new ArrayList<>();
        listListContacts.add(new ArrayList<>());

        for (Contact contact : contactList) {
            if (currentIndex++ < maxElements) {
                listListContacts.get(currentPage).add(contact);
            } else {
                listListContacts.add(new ArrayList<>());
                listListContacts.get(++currentPage).add(contact);
            }
        }

        // Allow user to change between the pages
        int numPages = listListContacts.size();
        while (true) {
            PrinterMenu.showContacts(listListContacts.get(currentPage), currentPage == 0, currentPage + 1 == numPages, true);
            if (listListContacts.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }
    // Method to create the menu showing all available Opportunities in a List
    private void showOpportunitiesMenu(ArrayList<Opportunity> opportunityList) {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentPage = 0;
        int currentIndex = 0;
        int decision;
        // List of opportunities is separated in multiple lists (pages)
        List<ArrayList<Opportunity>> listListOpportunity = new ArrayList<>();
        listListOpportunity.add(new ArrayList<>());

        for (Opportunity opportunity : opportunityList) {
            if (currentIndex++ < maxElements) {
                listListOpportunity.get(currentPage).add(opportunity);
            } else {
                listListOpportunity.add(new ArrayList<>());
                listListOpportunity.get(++currentPage).add(opportunity);
            }
        }

        // Allow user to change between the pages
        int numPages = listListOpportunity.size();
        while (true) {
            PrinterMenu.showOpportunities(listListOpportunity.get(currentPage), currentPage == 0, currentPage + 1 == numPages, true);
            if (listListOpportunity.size() > 1) {
                if (currentPage == 0) {
                    decision = promptMultipleDecisions("next", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            return;
                    }
                } else if (currentPage + 1 == numPages) {
                    decision = promptMultipleDecisions("previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage--;
                            break;
                        case 1:
                            return;
                    }
                } else {
                    decision = promptMultipleDecisions("next", "previous", "back");
                    switch (decision) {
                        case 0:
                            currentPage++;
                            break;
                        case 1:
                            currentPage--;
                        case 2:
                            return;
                    }
                }
            } else {
                promptDecision("enter");
                return;
            }
        }
    }

    //Method that handles the prompts to convert a lead
    private void promptConvert(int id) {
        // check if Lead exists, if not print error message
        if (db.hasLead(id)) {
            String contactName = db.getLeadHash().get(id).getName();
            //call methods to prompt Opportunity's product and quantity
            PrinterMenu.printMenu("convert");
            Product product = promptProduct();
            PrinterMenu.printMenu("convert", "product", product.toString());
            int quantity = promptPositiveNumber();
            //print also the contact (from the lead's info)
            PrinterMenu.printMenu("convert", "quantity and contact", Integer.toString(quantity), contactName);
            if (!promptDecision("enter back")) {
                return;
            }
            //call methods to prompt Account's industry, employee count, city and country
            PrinterMenu.printMenu("convert", "account");
            Industry industry = promptIndustry();
            PrinterMenu.printMenu("convert", "industry", industry.toString());
            int employeeCount = promptPositiveNumber();
            PrinterMenu.printMenu("convert", "employees", Integer.toString(employeeCount));
            String city = promptString("");
            PrinterMenu.printMenu("convert", "city", city);
            String country = promptString("");
            PrinterMenu.printMenu("convert", "country", country);
            if (promptDecision("enter back")) {
                db.convertLead(id, product, quantity, industry, employeeCount, city, country);
            }
        } else {
            PrinterMenu.setWarning("There is no lead with id " + id + " to convert!");
        }
    }

    // Method that handles the prompts to create a new lead
    private void promptLead() {
        // call methods to prompt name, phone number, email and company name
        PrinterMenu.printMenu("lead");
        String name = promptString("name");
        PrinterMenu.printMenu("lead", "name", name);
        String phoneNumber = promptString("phone");
        PrinterMenu.printMenu("lead", "phone", phoneNumber);
        String email = promptString("email");
        PrinterMenu.printMenu("lead", "email", email);
        String companyName = promptString("");
        PrinterMenu.printMenu("lead", "company", companyName);
        if (promptDecision("enter back")) {
            db.addLead(name, phoneNumber, email, companyName);
        }
    }

    // Method to ask for the user decision - one or two outcomes
    private boolean promptDecision(String decision) {
        String input;
        switch (decision) {
            case "enter back":
                do {
                    input = scanner.nextLine().trim().toLowerCase();
                    switch (input) {
                        case "":
                            return true;
                        case "back":
                            return false;
                    }
                    PrinterMenu.setWarning("Please input a valid command from the highlighted above!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                } while (true);
            case "enter":
                scanner.nextLine();
                return true;
            case "exit":
                do {
                    input = scanner.nextLine().trim().toLowerCase();
                    switch (input) {
                        case "":
                            return true;
                        case "exit":
                            return false;
                    }
                    PrinterMenu.setWarning("Please input a valid command from the highlighted above!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                } while (true);
        }
        return false;
    }

    // Method to ask for the user decision - more than 2 outcomes
    private int promptMultipleDecisions(String... choices){
        if (choices.length == 0) {
            throw new IllegalArgumentException();
        }
        String input;
        while (true) {
            input = scanner.nextLine().trim().toLowerCase();
            for (int i = 0; i < choices.length; i++) {
                if (input.equals(choices[i].trim().toLowerCase())) {
                    return i;
                }
            }
            PrinterMenu.setWarning("Please input a valid command from the highlighted above!");
            PrinterMenu.printMenu("");
            PrinterMenu.clearWarning();
        }
    }

    // prompt Product and validate
    private Product promptProduct() {
        String input;
        input = scanner.nextLine().trim().toUpperCase();
        while (!validProduct(input)) {
            PrinterMenu.setWarning("Please input a valid Product option!");
            PrinterMenu.printMenu("");
            PrinterMenu.clearWarning();
            input = scanner.nextLine().trim().toUpperCase();
        }
        return Product.valueOf(input);
    }

    //prompt Industry and validate
    private Industry promptIndustry() {
        String input;
        input = scanner.nextLine().trim().toUpperCase();
        while (!validIndustry(input)) {
            PrinterMenu.setWarning("Please input a valid Industry option!");
            PrinterMenu.printMenu("");
            PrinterMenu.clearWarning();
            input = scanner.nextLine().trim().toUpperCase();
        }
        return Industry.valueOf(input);
    }

    //prompt number and validate
    private int promptPositiveNumber() {
        String input = scanner.nextLine().trim();
        while (!isValidPositiveNumber(input)) {
            PrinterMenu.setWarning("Please input a valid integer number! Must be positive!");
            PrinterMenu.printMenu("");
            PrinterMenu.clearWarning();
            input = scanner.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    //prompt phone number / email / name / location and validate
    private String promptString(String checkCondition) {
        String input;
        switch (checkCondition) {
            case "phoneNumber":
                input = scanner.nextLine().trim();
                while (!validPhone(input)) {
                    PrinterMenu.setWarning("Please input a valid Phone Number!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                    input = scanner.nextLine().trim();
                }
                return input;
            case "email":
                input = scanner.nextLine().trim();
                while (!validEmail(input)) {
                    PrinterMenu.setWarning("Please input a valid Email!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                    input = scanner.nextLine().trim();
                }
                return input;
            case "name":
                input = scanner.nextLine().trim();
                while (!validName(input)) {
                    PrinterMenu.setWarning("Please input a valid Name!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                    input = scanner.nextLine().trim();
                }
                return input;
            default:
                input = scanner.nextLine().trim();
                while (!validPhone(input)) {
                    PrinterMenu.setWarning("Please input a non empty string!");
                    PrinterMenu.printMenu("");
                    PrinterMenu.clearWarning();
                    input = scanner.nextLine().trim();
                }
                return input;
        }
    }
}
