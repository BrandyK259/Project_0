package com.revature.model;

import com.revature.app.CustomerActions;

public class Customer{
    /* Table Info:
     *
     * Name:
     *          [customers]
     * Columns:
     *        PK[username][varchar] not null
     *          [password][varchar] not null
     *          [first_name][varchar] not null
     *          [last_name][varchar] not null
     *          [email][varchar] null
     *        FK[checking_acct_id][int] null
     *        FK[savings_acct_id][int] null
     *        FK[joint_acct_id][int] null
     */

    public String  username,
                   password,
                   firstName,
                   lastName,
                   email;
    public int checkingAcctId,
               savingsAcctId,
               jointAcctId;

    public Customer(){}

    public Customer( String username, String password, String firstName, String lastName, String email, int checkingAcctId, int savingsAcctId, int jointAcctId)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.checkingAcctId = checkingAcctId;
        this.savingsAcctId = savingsAcctId;
        this.jointAcctId = jointAcctId;
    }
    public void setFKnull(Customer c){
        c.checkingAcctId = 0;
        c.savingsAcctId = 0;
        c.jointAcctId = 0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", checkingAcctId=" + checkingAcctId +
                ", savingsAcctId=" + savingsAcctId +
                ", jointAcctId=" + jointAcctId +
                '}';
    }
}
