package com.company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class M1Tests {
    Customer customer;
    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1);
        customer.setName("Alex");

        AccountRecord ar1 = new AccountRecord();
        ar1.setCharge(1000);
        ar1.setChargeDate("01-01-2021");
        // customer.addCharge(ar1);
        List<AccountRecord> charges = customer.getCharges();
        charges.add(ar1);
    }

    // getBalance() Tests
    @Test
    public void shouldAddToPositive() {
        AccountRecord ar2 = new AccountRecord();
        ar2.setCharge(-2000);
        ar2.setChargeDate("02-02-2022");
        List<AccountRecord> charges = customer.getCharges();
        charges.add(ar2);

        assertEquals(-1000, customer.getBalance());
    }

    @Test
    public void shouldAddToNegative() {
        AccountRecord ar3 = new AccountRecord();
        ar3.setCharge(3000);
        ar3.setChargeDate("03-03-2023");
        // customer.addCharge(ar3);
        List<AccountRecord> charges = customer.getCharges();
        charges.add(ar3);

        assertEquals(4000, customer.getBalance());
    }

    // toString() Tests
    @Test
    public void customerInfoToStringFormatIsCorrect() {
        assertEquals("CUSTOMER INFO -->   ID: 1 | NAME: Alex | BALANCE: 1000", customer.toString());
    }
}