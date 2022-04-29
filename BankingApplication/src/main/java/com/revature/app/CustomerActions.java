package com.revature.app;

import com.revature.model.Account;

public class CustomerActions {

    //register with username and password

    public void register (String username, String password){

    };

    //apply to open account
    public void apply(String acctType, String userName){

    };

    //apply to open joint account with another customer
    public void apply(String acctType, String userName, String jointName){

    };

    //withdraw from account
    public void withdraw(String userName, String acctType, int amount){

    };

    //deposit into account
    public void deposit(Account acct, int amount){

    }

    //transfer from account into another account
    public void transfer(int amount, Account giveAcct, Account recieveAcct){

    }

}
