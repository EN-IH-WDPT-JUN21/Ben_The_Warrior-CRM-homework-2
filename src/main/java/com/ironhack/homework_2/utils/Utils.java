package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;

public class Utils {
    public static boolean validNumber(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validNumberGreaterThan(String num, int smallest) {
        try {
            int convertedNum = Integer.parseInt(num);
            return convertedNum > smallest;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validProduct(String product) {
        try {
            Product.valueOf(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validIndustry(String industry) {
        try {
            Industry.valueOf(industry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validName(String name) {
        return true;
    }

    public static boolean validEmail(String email) {
        return true;
    }

    public static boolean validPhone(String phone) {
        return true;
    }

    public static boolean validString(String str) {
        return true;
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
                                return validNumber(commandWords[2]);
                        }
                    }
                    return false;
                case "convert":
                case "close-won":
                case "close-lost":
                    return validNumber(commandWords[1]);
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
