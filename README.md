# Ben_The_Warrior-CRM-homework-2

## Table of Contents

1. [**Installation**](#Installation)
2. [**Introduction**](#Introduction)
    1. [Functions](#Functions)
3. [**Menu**](#Menu)
    1. [How to navigate the menu](#How-to-navigate-the-menu)
4. [**Help menu**](#Help-menu)
5. [**Description of the CRM Components**](#Description-of-the-CRM-Components)
    1. [Lead](#Lead)
    2. [Contact](#Contact)
    3. [Opportunity](#Opportunity)
6. [**Walkthrough CRM**](#Walkthrough-CRM)
    1. [Create New Lead](#Create-New-Lead)
    2. [Convert a Lead into an Opportunity](#Convert-a-Lead-into-an-Opportunity)
    3. [Close Won Opportunity](#Close-Won-Opportunity)
    4. [Close Lost Opportunity](#Close-Lost-Opportunity)
    5. [Search for specific Lead, Opportunity, Account or Contact](#Search-for-specific-Lead,-Opportunity,-Account-or-Contact)
    6. [List all Leads, Opportunities, Accounts or Contacts](#List-all-Leads,-Opportunities,-Accounts-or-Contacts)
    7. [Save the changed data](#Save-the-changed-data)
    8. [Save and exit the program](#Save-and-exit-the-program)
7. [**The Team**](#The-Team)

## Installation
Download the project from the repository.

Open the repository as a project on a IDE such as IntelliJ.

Run the Main.java file on the path:

 `./src/com/ironhack/homework_2/main`

## Introduction

Currently, companies seek to be able to keep their customers through a one-to-one relationship and thus be able to provide a better service and a shorter response time. In order to achieve this task, this project was born as a collaborative assignment work for the Ironhack bootcamp. This program called CRM (Customer Relationship Management) is aimed at improving the performance and relationship with customers of a company that sells trucks, the software allows you to keep track of all prospects, contacts, opportunities and accounts, through the visualization of the status of each of them. 

### Functions

* Lead management, tracking and status
* Creating contacts when a potential customer is interested in purchasing a product 
* Opportunities management
* Accounts management
* Save all changes made in the CRM

## Menu

For a better experience, a Menu was implemented where interaction with the user is friendly. This Menu consists of a help section where all the possible actions provided by the program are shown. This Menu consists of a help section where all the possible actions provided by the program are shown. Once a CRM operation has been selected, the menu prompts you to enter information about the topic of interest. Once you have finished using the CRM, it gives you the option to exit the program and save the entered records.

### How to navigate the menu

In order to navigate through the CRM, the user must enter the corresponding command to execute the action.

```
Enter help for a list of valid commands!
Enter exit to close the application!

```


- If the player wants to see the commands of the CRM, he must input: **help**.
- If the player wants to exit the program, he must input: **exit**.

## Help menu

If you typed **help**, the menu with all commands shows up.

```
“new lead” – to create a new Lead  
“convert <ID>”– to convert a Lead into an Opportunity    
“close-won <ID>”– for changing the Opportunity status to Close Won      
“close-lost <ID>”– for changing the Opportunity status to Close Lost
“lookup <OBJECT> <ID>”- to search for specific Lead, Opportunity, Account or Contact
“show <OBJECT PLURAL>” - to list all Leads, Opportunities, Accounts or Contacts
“help” – to show information that explains usage of available commands (the same as you see)

“save” – to save the changed data   
“exit” – to save and exit the program

```
-	where ID is a valid number assigned to one of the Leads/Opportunities in the database
-	where OBJECT is **Lead**, **Opportunity**, **Account** or **Contact**
-	where OBJECT PLURAL is **Leads**, **Opportunities**, **Accounts** or **Contacts**

## Description of the CRM Components

### Lead

Lead will be understood as a prospect, that is, a person who shows interest in the product. It is important to keep in mind that lead is the first instance of the relationship with a potential client. In the next section we will see what attributes a lead has.

### Contact

When a prospect or lead, in addition to showing interest, already requests a quote, then the Contact is generated, which will have the information of the lead.

### Opportunity

Let's understand an opportunity as a possible transaction between the client and the company, the opportunity has three possible states, these are:
* OPEN (if the client has not yet decided whether or not to accept the quote)
* CLOSED_WON (if the client accepted the quote)
* CLOSED_LOST (if the client did not accept the quote)

### Account

An account is the most detailed record of the client who has requested a quote or inquiry. This means that it is stored as a potential customer in the CRM history.


## Walkthrough CRM

### Create New Lead

If you typed „new lead” in the Main menu (point 1) or Help menu (point 2), then the menu for creating new lead shows up.

```
Create New Lead

      Name:
      
      Phone Number:
      
      Email:
      
      Company Name:
```
You can see there all the information that will be needed: name, phone number, email and company name.

You will be asked for this data, please type them in the terminal below:

* name of new Lead (for example “John Snow”)
```
Insert Lead Name: “John Snow”
```
* phone number of new Lead (for example “0012123456789”)
```
Insert Lead Phone Number: “0012123456789”
```
* email of new Lead for example “john.snow@gmail.com”
```
Insert Lead Email: “john.snow@gmail.com”
```
* company name this new lead works for (for example “The Wall”)
```
Insert Lead Company Name: “The Wall”
```

Next you will see all the data you typed, please hit:
  
* **ENTER** to confirm Lead creation or type
  
* **back** to cancel Lead creation.
  
Then Main menu shows up (back to point 1).

### Convert a Lead into an Opportunity

The command for converting a Lead into an Opportunity is shown below.

You have to type

```
convert <ID>
```
in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Leads in the database (for example “convert 1”).

When you type “convert 1” the program will ask to enter all the information that will be needed to Create New Opportunity.

```
Create New Opportunity

      Product:
      
      Quantity:
```

You will be asked for this data, please type them in the terminal below:

```
Insert Product type [HYBRID, FLATBED or BOX]:
```
In this case will be “HYBRID”

```
Insert Quantity:
```

We going to use as example 20.

Next you will see the data you typed together with Contact Name and Status OPEN.

```
Create New Opportunity

Product: HYBRID

Quantity: 20

Contact Name: Jose

Status: OPEN


ENTER - confirm Opportunity information | back - return to the main menu
```
Please hit:

* **ENTER** key to confirm Opportunity information or type
* **back** to return to the main menu.
  
If you hit ENTER, there will be window  with all the information that will be needed to Create New Account: Industry, Number of Employees, City and Country.

```
Create New Account

Industry:

Number of Employees:

City:

Country:
```
  
You will be asked for this data, please type them in the terminal below:

```
Insert Industry [PRODUCE, ECOMMERCE, MANUFACTURING, MEDICAL, or OTHER]:
```
For example type “PRODUCE”.

```
Insert Employee Count:
```
This time we take 10.

```
Insert City:
```
we choose “Madrid”.

```
Insert Country:
```
"Spain"
  
  
Next you will see the data you typed for New Account.

```
Create New Account

Industry: PRODUCE

Number of Employees: 10

City: Madrid

Country: Spain

ENTER - delete Lead and create Contact, Opportunity and Account | back - cancel Lead conversion
```
  
* Hit **ENTER** key to delete Lead and create Contact, Opportunity and Account or type
* **back** to cancel Lead conversion.
  
Then Main menu shows up (back to point 1).

### Close Won Opportunity

When an opportunity is accepted by the client, then the opportunity is closed as Close Won.

Procedure for changing the Opportunity status to Close_Won.

It will show up if you typed:

```
close-won <ID>
```
in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Opportunities in the database (for example “close-won 1”).

The status of this Opportunity is changed into CLOSED_WON and then Main menu shows up (back to point 1).

```
Available Opportunities 

Id: 1, Product: HYBRID, Quantity: 10, Decision Maker: Jose, Status: CLOSED_WON
```

### Close Lost Opportunity

In case the client rejects the quote then the opportunity will be closed as Close_Lost.

Procedure for changing the Opportunity status to Close_Lost.

It will show up if you typed:

```
close-lost <ID>
```
in the Main menu (point 1) or Help menu (point 2), where ID is a valid number assigned to one of the Opportunities in the database (for example “close-lost 2”).

The status of this Opportunity is changed into CLOSED_LOST and then Main menu shows up (back to point 1).

```
Available Opportunities 

Id: 1, Product: HYBRID, Quantity: 10, Decision Maker: Jose, Status: CLOSED_LOST
```
### Search for specific Lead, Opportunity, Account or Contact

Menu for searching for specific Lead, Opportunity, Account or Contact.
  
It will show up if you typed:

```
lookup <OBJECT> <ID>
```
in the Main menu (point 1) or Help menu (point 2)
  
* where ID is a valid number assigned to one of the Leads/Opportunities in the database,
* where OBJECT is “Lead”, “Opportunity”, “Account” or “Contact” 
  
(for example type “lookup Lead 2”). 
  
If there is Lead/Opportunity/Account/Contact with given ID, then all the information will be shown.
  
For example for Lead it will be Name, Phone Number, Email and Company Name.

```
Information of Lead with id 3

Name: simon

Phone Number: +4915254567956

Email: ms@uc.cl

Company Name: air

ENTER - return to the main menu
```
  
Please hit ENTER to return to the main menu.
  
Then Main menu shows up (back to point 1).
  
If there is no Lead/Opportunity/Account/Contact with given ID, then you will see warning for example “There is no Lead with id 1” and go back to the Main menu.

```
There is no Lead with id 1 
```

### List all Leads, Opportunities, Accounts or Contacts

Procedure for listing all Leads, Opportunities, Accounts or Contacts.
  
It will show up if you typed:
```
show <OBJECT PLURAL>
```
in the Main menu (point 1) or Help menu (point 2), 
  
* where OBJECT PLURAL is “Leads”, “Opportunities”, “Accounts” or “Contacts” 
  
(for example type “show Leads”). 

```
show leads
```
  
Then the list of Leads/Opportunities/Accounts/Contacts will appear with all the information.
  
For example for Leads it will be:

```
Available Leads

Id: 3, Name: simon, Email: ms@uc.cl, Phone: +4921561648232, Company: air


ENTER - return to the main menu 
```
  
Please hit **ENTER** to return to the main menu.
  
Then Main menu shows up (back to point 1).

### Save the changed data

Procedure for saving the changed data.
  
It will show up if you typed:

```
save
```
in the Main menu (point 1) or Help menu (point 2).
  
Then Main menu shows up (back to point 1).

### Save and exit the program

Procedure for saving and exiting the program.
  
It will show up if you typed:

```
exit
```
in the Main menu (point 1) or Help menu (point 2).
  
Then the menu shows:

```
Goodbye.


Do you want to save before exiting?
ENTER - save and exit
exit - exit
```
  
Please hit:
  
* **ENTER** – to save and exit, or type                                                                                                                           
* **exit** – to exit without saving
  
and the program stops.

## The Team

by:  
**Ben The Warrior**
- [Joaodss](https://github.com/Joaodss)
- [MigNeves](https://github.com/MigNeves)
- [ShaunRly](https://github.com/ShaunRly)
- [simonpedrorios](https://github.com/simonpedrorios)
- [UrszulaF](https://github.com/UrszulaF)
