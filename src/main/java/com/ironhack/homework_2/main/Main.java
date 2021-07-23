package com.ironhack.homework_2.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ironhack.homework_2.classes.Account;
import com.ironhack.homework_2.classes.Contact;
import com.ironhack.homework_2.classes.Lead;
import com.ironhack.homework_2.classes.Opportunity;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import com.ironhack.homework_2.utils.Printer;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Printer.printWarning();
//        Printer.clearCommandLine();
//        Printer.printError();
//        Printer.clearCommandLine();
//        Printer.printError();

        Printer.printMainMenu();

        Printer.clearCommandLine();
        Printer.printCreateLeadMenu();
        Printer.printCreateLeadMenu("João Afonso");
        Printer.printCreateLeadMenu("João Afonso", "joaoafonsodss@gmail.com");
        Printer.printCreateLeadMenu("João Afonso", "joaoafonsodss@gmail.com", "9496620672");
        Printer.printCreateLeadMenu("João Afonso", "joaoafonsodss@gmail.com", "9496620672", "Ironhack");
        Lead l = new Lead("João Afonso Lead", "9496620672", "joaoafonsodss@gmail.com", "Ironhack");

        Contact c = new Contact("João Afonso Contact", "9496620672", "joaoafonsodss@gmail.com", "Ironhack");


        Printer.clearCommandLine();
        Printer.printCreateOpportunityMenu();
        Printer.printCreateOpportunityMenu(Product.FLATBED);
        Printer.printCreateOpportunityMenu(Product.FLATBED, 120, "João Afonso");
        Opportunity o = new Opportunity(Product.FLATBED, 120, c, Status.OPEN);

        Printer.clearCommandLine();
        Printer.printCreateAccountMenu();
        Printer.printCreateAccountMenu(Industry.ECOMMERCE);
        Printer.printCreateAccountMenu(Industry.ECOMMERCE, 10);
        Printer.printCreateAccountMenu(Industry.ECOMMERCE, 10, "Porto");
        Printer.printCreateAccountMenu(Industry.ECOMMERCE, 10, "Porto", "Portugal");
        Account a = new Account(Industry.ECOMMERCE, 10, "Porto", "Portugal", c, o);

        Printer.clearCommandLine();
        Printer.printLeadInfo(l);
        Printer.printContactInfo(c);
        Printer.printOpportunityInfo(o);
        Printer.printAccountInfo(a);


    }
}
