package com.ironhack.homework_2.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ironhack.homework_2.classes.*;
import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import com.ironhack.homework_2.utils.Printer;
import com.ironhack.homework_2.utils.PrinterMenu;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {


        /*// ======================================== PrinterMenu testing ========================================
        Printer.printWarning();
        PrinterMenu.clearCommandLine();
        Printer.printError();

        PrinterMenu.clearCommandLine();
        PrinterMenu.printMainMenu();

        PrinterMenu.clearCommandLine();
        PrinterMenu.printCreateLeadMenu();
        PrinterMenu.printCreateLeadMenu("João Afonso");
        PrinterMenu.printCreateLeadMenu("João Afonso", "joao@gmail.com");
        PrinterMenu.printCreateLeadMenu("João Afonso", "joao@gmail.com", "949662350672");
        PrinterMenu.printCreateLeadMenu("João Afonso", "joao@gmail.com", "949662350672", "Ironhack");
        Lead l = new Lead("João Afonso Lead", "949662350672", "joao@gmail.com", "Ironhack");
        Contact c = new Contact("João Afonso Contact", "949662350672", "joao@gmail.com", "Ironhack");

        PrinterMenu.clearCommandLine();
        PrinterMenu.printCreateOpportunityMenu();
        PrinterMenu.printCreateOpportunityMenu(Product.FLATBED);
        PrinterMenu.printCreateOpportunityMenu(Product.FLATBED, 120, "João Afonso");
        Opportunity o = new Opportunity(Product.FLATBED, 120, c, Status.OPEN);

        PrinterMenu.clearCommandLine();
        PrinterMenu.printCreateAccountMenu();
        PrinterMenu.printCreateAccountMenu(Industry.ECOMMERCE);
        PrinterMenu.printCreateAccountMenu(Industry.ECOMMERCE, 10);
        PrinterMenu.printCreateAccountMenu(Industry.ECOMMERCE, 10, "Porto");
        PrinterMenu.printCreateAccountMenu(Industry.ECOMMERCE, 10, "Porto", "Portugal");
        Account a = new Account(Industry.ECOMMERCE, 10, "Porto", "Portugal", c, o);

        PrinterMenu.clearCommandLine();
        PrinterMenu.printLeadInfo(l);
        PrinterMenu.printContactInfo(c);
        PrinterMenu.printOpportunityInfo(o);
        PrinterMenu.printAccountInfo(a);*/

        Menu.mainMenu();
    }
}
