# Ben_The_Warrior-CRM-homework-2

# Table of Contents

# Introduction

Currently, companies seek to be able to keep their customers through a one-to-one relationship and thus be able to provide a better service and a shorter response time. In order to achieve this task, this project was born as a collaborative assignment work for the Ironhack bootcamp. This program is aimed at improving the performance and relationship with customers of a company that sells trucks, the software allows you to keep track of all prospects, contacts, opportunities and accounts, through the visualization of the status of each of them. 

## Functions

* Lead management
* Contact management
* Opportunities management
* Accounts management

# Menu

Please hit run to initialize the application.
## 1.	Main menu:
After that, the Main menu shows up.
Type in the terminal below one of the commands:
* “help” for a list of valid commands,
* “exit” to clase the application

You can type “help” or “exit” or one of the commands. If you need help with commands please type “help”
## 2.	Help menu
If you typed “help”, the menu with all commands shows up.

Please type in the terminal below one of the commands from the menu:

* “new lead” – to create a new Lead,                                                                                             
* “convert <ID>”– to convert a Lead into an Opportunity,                                                                               
* “close-won <ID>”– for changing the Opportunity status to Close Won,                                                                                                          
* “close-lost <ID>”– for changing the Opportunity status to Close Lost, 
* “lookup <OBJECT> <ID>”- to search for specific Lead, Opportunity, Account or Contact,                                                                
* “show <OBJECT PLURAL>” - to list all Leads, Opportunities, Accounts or Contacts,                                                                      
* “help” – to show information that explains usage of available commands (the same as you see),                                                                                                     
* “save” – to save the changed data,                                                                                                                   
* “exit” – to save and exit the program,  

-	where ID is a valid number assigned to one of the Leads/Opportunities in the database
-	where OBJECT is “Lead”, “Opportunity”, “Account” or “Contact”
-	where OBJECT PLURAL is “Leads”, “Opportunities”, “Accounts” or “Contacts”


## 3. Create New Lead

If you typed „new lead” in the Main menu (point 1) or Help menu (point 2), then the menu for creating new lead shows up. 
  
You can see there all the information that will be needed: name, phone number, email and company name.

You will be asked for this data, please type them in the terminal below:
  
* name of new Lead (for example “John Snow”),
* phone number of new Lead (for example “0012123456789”),
* email of new Lead for example “john.snow@gmail.com”,
* company name this new lead works for (for example “The Wall”).
  
Next you will see all the data you typed, please hit:
  
* ENTER to confirm Lead creation or type
  
* “back” to cancel Lead creation.
  
Then Main menu shows up (back to point 1).

## 4. Convert a Lead into an Opportunity

Menu for converting a Lead into an Opportunity.
  
It will show up if you typed „ convert <ID>” in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Leads in the database (for example “convert 1”). 
  
You can see there all the information that will be needed to Create New Opportunity: product and quantity.
  
You will be asked for this data, please type them in the terminal below:
  
* 	product type [HYBRID, FLATBED or BOX] (for example type “HYBRID”).
* quantity (for example “20”).

Next you will see the data you typed together with Contact Name and Status OPEN. Please hit:
  
* ENTER to confirm Opportunity information or type
* “back” to return to the main menu.
  
If you hit ENTER, there will be window  with all the information that will be needed to Create New Account: Industry, Number of Employees, City and Country.
  
You will be asked for this data, please type them in the terminal below:
  
* Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER] (for example type “PRODUCE”),
* Employee Count (for example type “10”),
* City (for example type “Madrid”),
* Country (for example type “Spain”).
  
Next you will see the data you typed for New Account. Please hit:
  
* ENTER to delete Lead and create Contact, Opportunity and Account or type
* “back” to cancel Lead conversion.
  
Then Main menu shows up (back to point 1).

## 5. Close Won Opportunity

Menu for changing the Opportunity status to Close Won.

It will show up if you typed „ close-won <ID>” in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Opportunities in the database (for example “close-won 1”). 

The status of this Opportunity is changed into CLOSED_WON and then Main menu shows up (back to point 1).

## 6. Close Lost Opportunity

Menu for changing the Opportunity status to Close Lost.
  
It will show up if you typed „ close-lost <ID>” in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Opportunities in the database (for example “close-lost 1”). 
  
The status of this Opportunity is changed into CLOSED_LOST and then Main menu shows up (back to point 1).


## 7. Search for specific Lead, Opportunity, Account or Contact

Menu for searching for specific Lead, Opportunity, Account or Contact.
  
It will show up if you typed „ lookup <OBJECT> <ID>” in the Main menu (point 1) or Help menu (point 2), 
  
* where ID is a valid number assigned to one of the Leads/Opportunities in the database,
* where OBJECT is “Lead”, “Opportunity”, “Account” or “Contact” 
  
(for example type “lookup Lead 2”). 
  
If there is Lead/Opportunity/Account/Contact with given ID, then all the information will be shown.
  
For example for Lead it will be Name, Phone Number, Email and Company Name.
  
Please hit ENTER to return to the main menu.
  
Then Main menu shows up (back to point 1).
  
If there is no Lead/Opportunity/Account/Contact with given ID, then you will see warning for example “There is no Lead with id 1” and go back to the Main menu.

## 8. List all Leads, Opportunities, Accounts or Contacts

Menu for listing all Leads, Opportunities, Accounts or Contacts.
  
It will show up if you typed „ show <OBJECT PLURAL>” in the Main menu (point 1) or Help menu (point 2), 
  
* where OBJECT PLURAL is “Leads”, “Opportunities”, “Accounts” or “Contacts” 
  
(for example type “show Leads”). 
  
Then the list of Leads/Opportunities/Accounts/Contacts will appear with all the information.
  
For example for Leads it will be Ids, Names, Emails, Phone Numbers and Company Names.
  
Please hit ENTER to return to the main menu.
  
Then Main menu shows up (back to point 1).

## 9. Save the changed data

Menu for saving the changed data.
  
It will show up if you typed „save” in the Main menu (point 1) or Help menu (point 2).
  
Then Main menu shows up (back to point 1).


## 10. Save and exit the program

Menu for saving and exiting the program.
  
It will show up if you typed „exit” in the Main menu (point 1) or Help menu (point 2).
  
Then the menu shows with information “Do you want to save before exiting?”
  
Please hit:
  
* ENTER – to save and exit, or type                                                                                                                           
* “exit” – to exit without saving
  
and the program stops.

