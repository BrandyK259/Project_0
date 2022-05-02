package com.revature.model;

import java.math.BigDecimal;

public class Account {
    /* Table Info:
     *
     * Name:
     *          [accounts]
     * Columns:
     *        PK[id][int] serial
     *          [account_type][varchar] not null
     *          [balance][money] null
     *          [is_approved][bool] null
     *          [is_active][bool] null
     *        FK[owner_username][varchar] null
     *        FK[joint_username][varchar] null
     */
    public int id;
    public String accountType;
    public BigDecimal balance;
    public boolean approved,
                   active;
    public String ownerUsername,
                  jointUsername;

    public Account(){}

    public Account(int id, String accountType, BigDecimal balance, boolean approved, boolean active, String ownerUsername, String jointUsername){
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.approved = approved;
        this.active = active;
        this.ownerUsername = ownerUsername;
        this.jointUsername = jointUsername;
    }

    public void setFKnull(Account a){
        a.ownerUsername = null;
        a.jointUsername = null;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", approved=" + approved +
                ", active=" + active +
                ", ownerUsername='" + ownerUsername + '\'' +
                ", jointUsername='" + jointUsername + '\'' +
                '}';
    }
}
