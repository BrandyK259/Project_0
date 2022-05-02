package com.revature.app;


import com.revature.model.Customer;

public class AdminActions extends EmployeeActions{
    //everything that a customer can do

    //everything that an employee can do

    //edit customer account information

    //edit customer account balances

    //edit customer personal information

    //cancel customer accounts

    public void accountActions(Customer c){
        System.out.println("Account actions include withdrawals, deposits, and transfers");
    }

    public void cancelAccount(Customer c){
        System.out.println("Account has been canceled");
    }
}
