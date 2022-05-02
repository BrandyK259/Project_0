package com.revature.app;

import com.revature.model.Customer;

public class EmployeeActions extends CustomerActions{
    //should be able to do everything that a customer can do

    //view customer info
    public void lookUpCustomer(Customer c){
        //get all info from accounts table for customer
        System.out.println("Customer account info: ");
    };

    //view customer account balances
    public void lookUpCustomerBalances(Customer c){
        //get the balance column info from all

        System.out.println("Customer account balances: ");
    };

    //view customer personal info
    public void findCustomer(Customer c){
        //get all info from customer table

        System.out.println("Customer personal info: ");
    };

    //approve open applications for accounts
    public void approveApplications(Customer c){
        //get all info from customer table

        System.out.println("Customer application approved");
    };

    //deny open applications for accounts
    public void denyApplications(Customer c){
        //get all info from customer table

        System.out.println("Customer application denied");
    };
}
