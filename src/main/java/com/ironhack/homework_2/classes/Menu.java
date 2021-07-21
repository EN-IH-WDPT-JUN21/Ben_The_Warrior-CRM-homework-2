package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;

import java.util.Scanner;

import static com.ironhack.homework_2.utils.JsonDatabaseUtility.*;
import static com.ironhack.homework_2.utils.Printer.print;
import static com.ironhack.homework_2.utils.Utils.*;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean isValidCommand(String command){
        String[] commandWords = command.trim().toLowerCase().split(" ");
        if (commandWords.length > 1){
            switch (commandWords[0]){
                case "new":
                case "show":
                    return commandWords.length == 2;
                case "lookup":
                    if (commandWords.length == 3){
                        switch (commandWords[1]){
                            case "lead":
                            case "opportunity":
                            case "account":
                            case "contact":
                                return validNumber(commandWords[2]);
                        }
                    }
                    return false;
                case "convert":
                    if (commandWords.length == 3 && commandWords[1].equals("lead")){
                        return validNumber(commandWords[2]);
                    }
                    return false;
                case "close_won":
                case "close_lost":
                    return validNumber(commandWords[1]);
                default:
                    return false;
            }
        }else{
            if ("help".equals(commandWords[0])) {
                return true;
            }
            return false;
        }
    }

    public static void computeCommand(){
        String input;
        while(true){
            input = scanner.nextLine();
            if (isValidCommand(input)){
                String[] inputArray = input.trim().toLowerCase().split(" ");
                switch (inputArray[0]){
                    case "new":
                        if (inputArray[1].equals("lead")){
                            promptLead();
                        }
                        break;
                    case "show":
                        if (inputArray[1].equals("leads")){
                            showLeads();
                        }
                        break;
                    case "lookup":
                        if (inputArray[1].equals("lead")){
                            if (validNumber(inputArray[2]))
                            print(lookupLead(Integer.parseInt(inputArray[2])).toString());
                        }
                        break;
                    case "convert":
                        promptConvert(Integer.parseInt(inputArray[1]));
                        break;
                    case "close_won":
                    case "close_lost":
                        changeStatus(Status.valueOf(inputArray[0].toUpperCase()), Integer.parseInt(inputArray[1]));
                        break;
                    case "help":
                        print("List of available commands:\n" +
                                "new lead: creates new lead\n" +
                                "show leads: shows all existing leads\n" +
                                "lookup lead id: shows lead with the corresponding id provided\n" +
                                "convert id: converts lead with the corresponding id provided\n" +
                                "close_won id: change opportunity status to close won\n" +
                                "close_lost id: change opportunity status to close lost\n" +
                                "help: print list of available commands");
                        break;
                }
            }else{
                print("There is no such command as \"" + input + "\"! To see the list of available commands type help");
            }
        }
    }

    private static void promptConvert(int id){
        // prompt Opportunity
        Product product = promptProduct("Please provide the product to be bought");
        int quantity = promptNumber("Please provide the quantity of products");

        //prompt Account
        Industry industry = promptIndustry("Please provide the industry associated with the new account");
        int employeeCount = promptNumber("Please provide the number of employees of the organization");
        String city = promptString("Please provide the organization's city","");
        String country = promptString("Please provide the organization's country","");

        convertLead(id, product, quantity, industry, employeeCount, city, country);
    }

    private static int promptLead() {
        // name, phoneNumber, email, and companyName
        String name = promptString("Please provide the name to be associated with the lead", "name");
        String phoneNumber = promptString("Please provide the phone number to associate with the lead", "phone");
        String email = promptString("Please provide the email to associate with the lead", "email");
        String companyName = promptString("Please provide the name of the company to associate with the lead", "");
        return addLead(name, phoneNumber, email, companyName);
    }

    private static Product promptProduct(String prompt){
        String input;
        do{
            print(prompt);
            input = scanner.nextLine();
        } while(!validProduct(input));
        return Product.valueOf(input);
    }
    private static Industry promptIndustry(String prompt){
        String input;
        do{
            print(prompt);
            input = scanner.nextLine();
        } while(!validIndustry(input));
        return Industry.valueOf(input);
    }
    private static int promptNumber(String prompt){
        String input;
        do{
            print(prompt);
            input = scanner.nextLine();
        } while(!validNumber(input));
        return Integer.parseInt(input);
    }
    private static String promptString(String prompt, String checkCondition){
        String input;
        switch (checkCondition){
            case "phoneNumber":
                do{
                    print(prompt);
                    input = scanner.nextLine();
                } while(!validPhone(input));
                return input;
            case "email":
                do{
                    print(prompt);
                    input = scanner.nextLine();
                } while(!validEmail(input));
                return input;
            case "name":
                do{
                    print(prompt);
                    input = scanner.nextLine();
                } while(!validName(input));
                return input;
            default:
                do{
                    print(prompt);
                    input = scanner.nextLine();
                } while(!validString(input));
                return input;
        }
    }
}
