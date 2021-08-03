package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isValidPositiveNumber(String num) {
    // Method to validate a number inputted by the user
        try {
            int convertedNum = Integer.parseInt(num);
            return convertedNum >= 0;
        } catch (Exception e) {
            return false;
        }
    }
    // Method to validate a Product inputted by the user
    public static boolean validProduct(String product) {
        try {
            Product.valueOf(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // Method to validate a Industry inputted by the user
    public static boolean validIndustry(String industry) {
        try {
            Industry.valueOf(industry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // Method to validate a name inputted by the user
    public static boolean validName(String name){
        return Pattern.compile("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$").matcher(name).find();
    }
    // Method to validate an email inputted by the user
    public static boolean validEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return emailPattern.matcher(email).find();
    }
    // Method to validate a phone number inputted by the user
    public static boolean validPhone(String phone){
        String patterns = "^\\s?((\\+[1-9]{1,4}[ \\-]*)" +
                "|(\\([0-9]{2,3}\\)[ \\-]*)" +
                "|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
        Pattern phoneNumRegexes = Pattern.compile(patterns);
        return phoneNumRegexes.matcher(phone).find();
    }
    // Method to validate a generic string inputted by the user
    public static boolean validString(String str){
        if (str.length() < 1) return false;
        return Pattern.compile("^[A-ZÀ-Ü]+([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]+$").matcher(str).find();
    }


    // Check for the command syntax in terms of number of parameters and validity of parameters
    public static boolean isValidCommand(String command) {

        String[] commandWords = command.trim().toLowerCase().split(" ");
        if (commandWords.length > 1) {
            switch (commandWords[0]) {
                case "new":
                    return commandWords[1].equals("lead") && commandWords.length == 2;
                case "show":
                    switch (commandWords[1]) {
                        case "leads":
                        case "opportunities":
                        case "accounts":
                        case "contacts":
                            return commandWords.length == 2;
                    }
                    return false;
                case "lookup":
                    if (commandWords.length == 3) {
                        switch (commandWords[1]) {
                            case "lead":
                            case "opportunity":
                            case "account":
                            case "contact":
                                return isValidPositiveNumber(commandWords[2]);
                        }
                    }
                    return false;
                case "convert":
                case "close-won":
                case "close-lost":
                    return isValidPositiveNumber(commandWords[1]);
                default:
                    return false;
            }
        } else {
            switch (commandWords[0]) {
                case "help":
                case "exit":
                case "save":
                    return true;
                default:
                    return false;
            }
        }
    }
}
