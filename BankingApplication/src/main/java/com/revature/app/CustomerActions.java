package com.revature.app;

import com.revature.model.Account;
import com.revature.model.Customer;

public class CustomerActions {

    //register with username and password

    public void register (String username, String password){
        System.out.println("Registering with bank");
    };

    //apply to open account
    public void apply(String acctType, String userName){
        switch (acctType){
            case "checking":
                System.out.println("Applying for checking account");
                break;
            case "savings":
                System.out.println("Applying for savings account");
                break;
            case "joint":
                System.out.println("Applying for joint account");
                break;
        }
    };

    //apply to open joint account with another customer
    public void apply(String acctType, String userName, String jointName){
        System.out.println("Applying for joint account");
    };

    public void accountActions(Customer c){

        System.out.println("Account actions include withdrawals, deposits, and transfers");
    }
    //withdraw from account
    public void withdrawal(String userName, String acctType, int amount){

    };

    //deposit into account
    public void deposit(Account acct, int amount){

    }

    //transfer from account into another account
    public void transfer(int amount, Account giveAcct, Account recieveAcct){

    }

}
