package com.ironhack.homework_2.classes;

import com.ironhack.homework_2.enums.Product;
import com.ironhack.homework_2.enums.Status;
import com.ironhack.homework_2.utils.JsonDatabaseUtility;
import com.ironhack.homework_2.utils.PrinterMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    JsonDatabaseUtility initialDatabase;
    ByteArrayInputStream input;
    Menu menu;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        initialDatabase = JsonDatabaseUtility.loadDatabaseInJson();

        input = new ByteArrayInputStream("new lead\nBen\n123456789\nben@ironhack.com\nIronhack\n \nexit\n ".getBytes());

        menu = new Menu(input);
        menu.mainMenu();
    }


    @AfterEach
    void tearDown() throws IOException {
        JsonDatabaseUtility.saveDatabaseInJson(initialDatabase);
    }


    @Test
    @DisplayName("Start menu with nonexistent database")
    void mainMenu_UnavailableDatabase_OpenEmptyDatabase(){
        File file = new File("src/main/java/com/ironhack/homework_2/database/database.json");
        boolean fileDeleted = file.delete();

        assertTrue(fileDeleted);
        menu = new Menu();

        JsonDatabaseUtility menuDatabase = menu.getDatabase();
        assertEquals(0,menuDatabase.getLeadHash().size());
        assertEquals(0,menuDatabase.getContactHash().size());
        assertEquals(0,menuDatabase.getOpportunityHash().size());
        assertEquals(0,menuDatabase.getAccountHash().size());

    }

    @Test
    @DisplayName("Print help menu when command help")
    void mainMenu_HelpCommand_PrintHelpMenu() {

        PrintStream sysOutBackup = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        input = new ByteArrayInputStream("help\nexit\nexit".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("new lead"));
        assertTrue(output.contains("convert <ID>"));
        assertTrue(output.contains("close-won <ID>"));
        assertTrue(output.contains("close-lost <ID>"));
        assertTrue(output.contains("lookup <OBJECT> <ID>"));
        assertTrue(output.contains("show <OBJECT PLURAL>"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Add prompted lead to the database")
    void mainMenu_NewLeadCommand_AddNewLead() throws FileNotFoundException {

        JsonDatabaseUtility databaseBeforeAddingLead = JsonDatabaseUtility.loadDatabaseInJson();
        int initialSize = databaseBeforeAddingLead.getLeadHash().size();

        input = new ByteArrayInputStream("new lead\nJohn\n987654321\njohn@ironhack.com\nIronhack\n \nexit\n ".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        JsonDatabaseUtility databaseAfterAddingLead = JsonDatabaseUtility.loadDatabaseInJson();
        int finalSize = databaseAfterAddingLead.getLeadHash().size();

        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Add Contact, Opportunity and Account when converting Lead")
    void mainMenu_ConvertCommand_NewLOpportunityAccountContact() throws FileNotFoundException {
        JsonDatabaseUtility databaseBeforeConverting = JsonDatabaseUtility.loadDatabaseInJson();
        int initialLeadSize = databaseBeforeConverting.getLeadHash().size();
        int initialContactSize = databaseBeforeConverting.getContactHash().size();
        int initialOpportunitySize = databaseBeforeConverting.getOpportunityHash().size();
        int initialAccountSize = databaseBeforeConverting.getAccountHash().size();

        input = new ByteArrayInputStream("convert 1\nhybrid\n200\n \nOtHer\n1\nLisbon\nPortugal\n \nexit\n ".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        JsonDatabaseUtility databaseAfterConverting = JsonDatabaseUtility.loadDatabaseInJson();
        int finalLeadSize = databaseAfterConverting.getLeadHash().size();
        int finalContactSize = databaseAfterConverting.getContactHash().size();
        int finalOpportunitySize = databaseAfterConverting.getOpportunityHash().size();
        int finalAccountSize = databaseAfterConverting.getAccountHash().size();

        assertEquals(initialLeadSize - 1, finalLeadSize);
        assertEquals(initialContactSize + 1, finalContactSize);
        assertEquals(initialOpportunitySize + 1, finalOpportunitySize);
        assertEquals(initialAccountSize + 1, finalAccountSize);
    }

    @Test
    @DisplayName("Display error message of invalid command")
    void mainMenu_InvalidCommand_ErrorMessage() {

        PrintStream sysOutBackup = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        input = new ByteArrayInputStream("invalid command\nexit\nexit".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("There is no such command as \"invalid command\"! To see the list of available commands type help!"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Change status to close-won")
    void mainMenu_CloseWon_ChangeStatusToCloseWon() throws FileNotFoundException {

        input = new ByteArrayInputStream("convert 1\nhybrid\n200\n \nOtHer\n1\nLisbon\nPortugal\n \nclose-won 1\nexit\n ".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        JsonDatabaseUtility databaseAfterStatusChange = JsonDatabaseUtility.loadDatabaseInJson();

        assertEquals(Status.CLOSED_WON, databaseAfterStatusChange.getOpportunityHash().get(1).getStatus());
    }

    @Test
    @DisplayName("Change status to close-lost")
    void mainMenu_CloseLost_ChangeStatusToCloseLost() throws FileNotFoundException {

        input = new ByteArrayInputStream("convert 1\nhybrid\n200\n \nOtHer\n1\nLisbon\nPortugal\n \nclose-lost 1\nexit\n ".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        JsonDatabaseUtility databaseAfterStatusChange = JsonDatabaseUtility.loadDatabaseInJson();

        assertEquals(Status.CLOSED_LOST, databaseAfterStatusChange.getOpportunityHash().get(1).getStatus());
    }

    @Test
    @DisplayName("Display error message of nonexistent object to lookup")
    void mainMenu_LookupNonexistentObject_ErrorMessage() {

        PrintStream sysOutBackup = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        input = new ByteArrayInputStream("lookup lead 5\nlookup contact 5\nlookup opportunity 5\nlookup account 5\nexit\nexit".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("There is no Lead with id 5"));
        assertTrue(output.contains("There is no Contact with id 5"));
        assertTrue(output.contains("There is no Opportunity with id 5"));
        assertTrue(output.contains("There is no Account with id 5"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Display error message of nonexistent object to lookup")
    void mainMenu_LookupExistentObject_ValidLookup() {

        PrintStream sysOutBackup = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        input = new ByteArrayInputStream(("new lead\nZe\n101\nze@bino.com\nCompany\n \nconvert 2\nbox\n100\n \nEcommerce" +
                "\n200\nMadrid\nSpain\n \nlookup lead 1\n \nlookup contact 1\n \nlookup opportunity 1\nback\n" +
                "lookup account 1\nback\nexit\nexit").getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("Ben") && output.contains("123456789") &&
                output.contains("ben@ironhack.com") && output.contains("Ironhack"));
        assertTrue(output.contains("Ze") && output.contains("101") &&
                output.contains("ze@bino.com") && output.contains("Company"));
        assertTrue(output.contains("BOX") && output.contains("100") && output.contains("OPEN"));
        assertTrue(output.contains("ECOMMERCE") && output.contains("200") &&
                output.contains("Madrid") && output.contains("Spain"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Show objects")
    void mainMenu_ShowObjects_ShowExistingObjects() {

        PrintStream sysOutBackup = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        input = new ByteArrayInputStream("show leads\n \nshow contacts\n \nshow opportunities\nback\nshow accounts\n \nexit\nexit".getBytes());
        menu = new Menu(input);

        menu.mainMenu();

        String output = outputStream.toString();
        assertTrue(output.contains("There are no Leads") || output.contains("Available Leads"));
        assertTrue(output.contains("There are no Contacts") || output.contains("Available Contacts"));
        assertTrue(output.contains("There are no Opportunities") || output.contains("Available Opportunities"));
        assertTrue(output.contains("There are no Accounts") || output.contains("Available Accounts"));
        System.setOut(sysOutBackup);
    }

    @Test
    @DisplayName("Compute help command")
    void computeCommand_HelpCommand_ShowHelpTrue() {

        menu = new Menu();

        assertFalse(menu.isShowHelp());
        menu.computeCommand("help");
        assertTrue(menu.isShowHelp());
    }

    @Test
    @DisplayName("Compute new lead command")
    void computeCommand_NewLeadCommand_AddNewLead() throws FileNotFoundException {

        JsonDatabaseUtility databaseBeforeAddingLead = JsonDatabaseUtility.loadDatabaseInJson();
        int initialSize = databaseBeforeAddingLead.getLeadHash().size();

        input = new ByteArrayInputStream("John\n987654321\njohn@ironhack.com\nIronhack\n ".getBytes());
        menu = new Menu(input);

        menu.computeCommand("new lead");

        JsonDatabaseUtility databaseAfterAddingLead = menu.getDatabase();
        int finalSize = databaseAfterAddingLead.getLeadHash().size();

        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    @DisplayName("Compute convert command")
    void computeCommand_ConvertCommand_NewLOpportunityAccountContact() throws FileNotFoundException {
        JsonDatabaseUtility databaseBeforeConverting = JsonDatabaseUtility.loadDatabaseInJson();
        int initialLeadSize = databaseBeforeConverting.getLeadHash().size();
        int initialContactSize = databaseBeforeConverting.getContactHash().size();
        int initialOpportunitySize = databaseBeforeConverting.getOpportunityHash().size();
        int initialAccountSize = databaseBeforeConverting.getAccountHash().size();

        input = new ByteArrayInputStream("hybrid\n200\n \nOtHer\n1\nLisbon\nPortugal\n ".getBytes());
        menu = new Menu(input);

        menu.computeCommand("convert 1");

        JsonDatabaseUtility databaseAfterConverting = menu.getDatabase();
        int finalLeadSize = databaseAfterConverting.getLeadHash().size();
        int finalContactSize = databaseAfterConverting.getContactHash().size();
        int finalOpportunitySize = databaseAfterConverting.getOpportunityHash().size();
        int finalAccountSize = databaseAfterConverting.getAccountHash().size();

        assertEquals(initialLeadSize - 1, finalLeadSize);
        assertEquals(initialContactSize + 1, finalContactSize);
        assertEquals(initialOpportunitySize + 1, finalOpportunitySize);
        assertEquals(initialAccountSize + 1, finalAccountSize);
    }

    @Test
    @DisplayName("Compute close-won command")
    void computeCommand_CloseWon_ChangeStatusToCloseWon() throws FileNotFoundException {
        menu = new Menu();

        JsonDatabaseUtility menuDatabase = menu.getDatabase();
        menuDatabase.addOpportunity(Product.BOX, 100,
                new Contact("John", "123", "john@ironhack.com", "Ironhack"));

        menu.computeCommand("close-won 1");

        assertEquals(Status.CLOSED_WON, menuDatabase.getOpportunityHash().get(1).getStatus());
    }

    @Test
    @DisplayName("Compute close-lost command")
    void computeCommand_CloseLost_ChangeStatusToCloseLost() throws FileNotFoundException {
        menu = new Menu();

        JsonDatabaseUtility menuDatabase = menu.getDatabase();
        menuDatabase.addOpportunity(Product.BOX, 100,
                new Contact("John", "123", "john@ironhack.com", "Ironhack"));

        menu.computeCommand("close-lost 1");

        assertEquals(Status.CLOSED_LOST, menuDatabase.getOpportunityHash().get(1).getStatus());
    }

    @Test
    @DisplayName("Compute lookup with nonexistent id")
    void computeCommand_LookupNonexistentObject_ErrorMessage() {

        menu = new Menu();

        menu.computeCommand("lookup lead 5");
        assertEquals(PrinterMenu.getWarning(), "There is no Lead with id 5");
        menu.computeCommand("lookup contact 5");
        assertEquals(PrinterMenu.getWarning(), "There is no Contact with id 5");
        menu.computeCommand("lookup opportunity 5");
        assertEquals(PrinterMenu.getWarning(), "There is no Opportunity with id 5");
        menu.computeCommand("lookup account 5");
        assertEquals(PrinterMenu.getWarning(), "There is no Account with id 5");
    }
}

