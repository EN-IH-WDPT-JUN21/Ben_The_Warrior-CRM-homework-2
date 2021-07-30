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

import java.util.*;

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
            if (showHelp) {
                PrinterMenu.printMenu("help");
                showHelp = false;
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

    public static boolean computeCommand(String input) {
        String[] inputArray = input.trim().toLowerCase().split(" ");
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
                    Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[1]));
                    opportunity.setStatus(Status.CLOSED_WON);
                } catch (IllegalArgumentException e) {
                    PrinterMenu.setWarning(e.getMessage());
                }
                break;
            case "close-lost":
                try {
                    Opportunity opportunity = db.lookupOpportunityId(Integer.parseInt(inputArray[1]));
                    opportunity.setStatus(Status.CLOSED_LOST);
                } catch (IllegalArgumentException e) {
                    PrinterMenu.setWarning(e.getMessage());
                }
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

    private static void lookupAccountMenu(Account account) {
        PrinterMenu.lookupObject(account);
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

    private static void showLeadsMenu() {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        TreeMap<Integer, Lead> leadTreeMap = new TreeMap<>(db.getLeadHash());
        List<ArrayList<Lead>> listListLead = new ArrayList<>();
        listListLead.add(new ArrayList<>());
        Set<Map.Entry<Integer, Lead>> entryLeadSet = leadTreeMap.entrySet();

        // for-each loop
        for (Map.Entry<Integer, Lead> entry : entryLeadSet) {
            if (currentIndex++ < maxElements) {
                listListLead.get(currentPage).add(entry.getValue());
            } else {
                listListLead.add(new ArrayList<>());
                listListLead.get(++currentPage).add(entry.getValue());
            }
        }
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

    private static void showOpportunitiesMenu() {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        TreeMap<Integer, Opportunity> opportunityTreeMap = new TreeMap<>(db.getOpportunityHash());
        List<ArrayList<Opportunity>> listListOpportunity = new ArrayList<>();
        listListOpportunity.add(new ArrayList<>());
        Set<Map.Entry<Integer, Opportunity>> entryOpportunitySet = opportunityTreeMap.entrySet();

        // for-each loop
        for (Map.Entry<Integer, Opportunity> entry : entryOpportunitySet) {
            if (currentIndex++ < maxElements) {
                listListOpportunity.get(currentPage).add(entry.getValue());
            } else {
                listListOpportunity.add(new ArrayList<>());
                listListOpportunity.get(++currentPage).add(entry.getValue());
            }
        }
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

    private static void showAccountsMenu() {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        TreeMap<Integer, Account> accountTreeMap = new TreeMap<>(db.getAccountHash());
        List<ArrayList<Account>> listListAccount = new ArrayList<>();
        listListAccount.add(new ArrayList<>());
        Set<Map.Entry<Integer, Account>> entryAccountSet = accountTreeMap.entrySet();

        // for-each loop
        for (Map.Entry<Integer, Account> entry : entryAccountSet) {
            if (currentIndex++ < maxElements) {
                listListAccount.get(currentPage).add(entry.getValue());
            } else {
                listListAccount.add(new ArrayList<>());
                listListAccount.get(++currentPage).add(entry.getValue());
            }
        }
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

    private static void showContactsMenu() {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentIndex = 0;
        int currentPage = 0;
        TreeMap<Integer, Contact> contactTreeMap = new TreeMap<>(db.getContactHash());
        List<ArrayList<Contact>> listListContact = new ArrayList<>();
        listListContact.add(new ArrayList<>());
        Set<Map.Entry<Integer, Contact>> entryContactSet = contactTreeMap.entrySet();

        // for-each loop
        for (Map.Entry<Integer, Contact> entry : entryContactSet) {
            if (currentIndex++ < maxElements) {
                listListContact.get(currentPage).add(entry.getValue());
            } else {
                listListContact.add(new ArrayList<>());
                listListContact.get(++currentPage).add(entry.getValue());
            }
        }
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

    private static void showContactsMenu(ArrayList<Contact> contactList) {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentPage = 0;
        int currentIndex = 0;
        int decision;
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

    private static void showOpportunitiesMenu(ArrayList<Opportunity> opportunityList) {
        int maxElements = PrinterMenu.getPrintMultipleObjectsMax();
        int currentPage = 0;
        int currentIndex = 0;
        int decision;
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

    private static void promptConvert(int id) {
        if (db.hasLead(id)) {
            String contactName = db.getLeadHash().get(id).getName();
            // prompt Opportunity
            PrinterMenu.printMenu("convert");
            Product product = promptProduct();
            PrinterMenu.printMenu("convert", "product", product.toString());
            int quantity = promptNumber();

            //prompt Account
            PrinterMenu.printMenu("convert", "quantity and contact", Integer.toString(quantity), contactName);
            if (!promptDecision("enter back")) {
                return;
            }
            PrinterMenu.printMenu("convert", "account");
            Industry industry = promptIndustry();
            PrinterMenu.printMenu("convert", "industry", industry.toString());
            int employeeCount = promptNumber();
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

    private static void promptLead() {
        // name, phoneNumber, email, and companyName
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

    private static boolean promptDecision(String decision) {
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
        }
        return false;
    }

    private static int promptMultipleDecisions(String... choices) {
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

    private static Product promptProduct() {
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

    private static Industry promptIndustry() {
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

    private static int promptNumber() {
        String input = scanner.nextLine().trim();
        while (!validNumber(input)) {
            PrinterMenu.setWarning("Please input a valid integer number!");
            PrinterMenu.printMenu("");
            PrinterMenu.clearWarning();
            input = scanner.nextLine().trim();
        }
        return Integer.parseInt(input);
    }

    private static String promptString(String checkCondition) {
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
