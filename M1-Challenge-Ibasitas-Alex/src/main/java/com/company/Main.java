package com.company;

// import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 
/*
Questions for Office Hours:

0. How to handle the zero case for positive and negative accounts?
1. Can we create a new method addCharge() to add to the list of charges in Customer since the attribute is private?
    index the customer
2. What's the right format for toString()? Also do we return a string in toString() or print the string?
3. Precisely what is printed to Main? Rubric only specifies to print positive and negative accounts
4. How exhaustive to test cases have to be in terms of the situations covered?
5. What's the difference between "Add the charges to the balance for each customer—10%" and "Sum the charges to get the balance for a customer—10%"
6. "Added the charges for each Customer to the appropriate Customer List<AccountRecords>" Aren't charges added to Customer objects, not Customer List<AccountRecords>?

*/


public class Main {

    private static List<String[]> customerData = Arrays.asList(
            // {customer ID, customer NAME, then list of AccountRecord -> AccountRecord charge, AccountRecord chargeDate}
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","-7500","01-10-2022"},
            new String[] {"1","Wayne Enterprises","18000","12-22-2021"},
            new String[] {"3","Ace Chemical","-48000","01-10-2022"},
            new String[] {"3","Ace Chemical","-95000","12-15-2021"},
            new String[] {"1","Wayne Enterprises","175000","01-01-2022"},
            new String[] {"1","Wayne Enterprises","-35000","12-09-2021"},
            new String[] {"1","Wayne Enterprises","-64000","01-17-2022"},
            new String[] {"3","Ace Chemical","70000","12-29-2022"},
            new String[] {"2","Daily Planet","56000","12-13-2022"},
            new String[] {"2","Daily Planet","-33000","01-07-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"},
            new String[] {"2","Daily Planet","33000","01-17-2022"},
            new String[] {"3","Ace Chemical","140000","01-25-2022"},
            new String[] {"2","Daily Planet","5000","12-12-2022"},
            new String[] {"3","Ace Chemical","-82000","01-03-2022"},
            new String[] {"1","Wayne Enterprises","10000","12-01-2021"}
    );



    public static void main(String[] args) {
        List<Customer> customerList = new ArrayList<>();    // Store Customer Information
        List<String> addedCustomers = new ArrayList<>();    // Store History of Customers Already Added

        // Onboard new Customers and Update Customer charges
        for (String[] stringArray : customerData) {
            // Customer and AccountRecord attributes
            int id = Integer.parseInt(stringArray[0]);
            String name = stringArray[1];
            int charge = Integer.parseInt(stringArray[2]);
            String chargeDate = stringArray[3];

            if (addedCustomers.contains(name)) {
                // Customer already in customerList, Update Customer charges
                AccountRecord accountRecord = new AccountRecord();   // New AccountRecord to insert into charges
                accountRecord.setCharge(charge);
                accountRecord.setChargeDate(chargeDate);

                // Locate correct Customer in customerList and add the charge
                for (Customer customer : customerList) {
                    if (customer.getName() == name) {
                        // customer.addCharge(accountRecord);
                        List<AccountRecord> charges = customer.getCharges();
                        charges.add(accountRecord);
                    }
                }
            } else {
                // Onboard a new customer
                addedCustomers.add(name);                             // update addedCustomers list
                Customer newCustomer = new Customer();                // new Customer created, onboarding occuring.

                newCustomer.setId(id);                                // set Customer ID

                newCustomer.setName(name);                            // set Customer name

                AccountRecord accountRecord = new AccountRecord();    // New AccountRecord to insert into charges List
                accountRecord.setCharge(charge);
                accountRecord.setChargeDate(chargeDate);
                // newCustomer.addCharge(accountRecord);
                List<AccountRecord> charges = newCustomer.getCharges();
                charges.add(accountRecord);

                customerList.add(newCustomer);  // Add the new completed customer to the customerList
            }
        }

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Print All Customers Info:");
        printAllCustomersInfo(customerList);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Print Customers as String:");
        printCustomerListToString(customerList);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Positive accounts:");
        // As a user I would like to access a list of all accounts that are positive
        List<Customer> positiveAccounts = getPositiveAccounts(customerList);
        printCustomerListToString(positiveAccounts);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Negative accounts:");
        // As a user I would like to access a list of all accounts that are negative.
        List<Customer> negativeAccounts = getNegativeAccounts(customerList);
        printCustomerListToString(negativeAccounts);
    }

    public static void printAllCustomersInfo(List<Customer> customerList) {
        // Print the Customers
        for(Customer c : customerList) {
            System.out.println("ID: " + c.getId());
            System.out.println("NAME: " + c.getName());
            System.out.println("CHARGES:");
            for(AccountRecord ar : c.getCharges()) {
                System.out.print("AMOUNT: " + ar.getCharge() + " | ");
                System.out.println("DATE: " + ar.getChargeDate());
            }
        }
    }

    public static void printCustomerListToString(List<Customer> customerList) {
        // printing the customer ID, customer name, and customer balance via toString() method
        for(Customer c : customerList) {
            System.out.println(c.toString());
        }
    }

    public static List<Customer> getPositiveAccounts(List<Customer> customerList) {
        List<Customer> positiveAccounts = new ArrayList<>();
        for(Customer c : customerList) {
            if (c.getBalance() >= 0) {
                positiveAccounts.add(c);
            }
        }
        return positiveAccounts;
    }

    public static List<Customer> getNegativeAccounts(List<Customer> customerList) {
        List<Customer> negativeAccounts = new ArrayList<>();
        for(Customer c : customerList) {
            if (c.getBalance() < 0) {
                negativeAccounts.add(c);
            }
        }
        return negativeAccounts;
    }
}
