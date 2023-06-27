package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<AccountRecord> charges = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Updated getBalance() method to sum all charges
    public int getBalance() {
        int balance = 0;
        for(AccountRecord ar : charges) {
            balance += ar.getCharge();
        }
        return balance;
    }

    public List<AccountRecord> getCharges() {
        return charges;
    } // returns a reference, take customer index and add accountRecord

    // public void addCharge(AccountRecord accountRecord) {this.charges.add(accountRecord); } // CAN I ADD THIS?

    // Updated toString() method to print / return the customer ID, customer name, and customer balance
    @Override
    public String toString() {
        // Alternatively
        return "CUSTOMER INFO -->   ID: " + id + " | NAME: " + name + " | BALANCE: " + Integer.toString(this.getBalance());
    }
}
