package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Admin;
import com.revature.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAOInterface<Account, Customer>{
    /* Table Info:
     *
     * Name:
     *          [accounts]
     * Columns:
     *          [id][int] serial
     *          [account_type][varchar] not null
     *          [balance][money] null
     *          [is_approved][bool] null
     *          [is_active][bool] null
     *          [owner_username][varchar]null
     *          [joint_username][varchar]null
     */

    @Override
    public void create(Account a) {
        Connection conn = ConnectionManager.getConnection();

            try{
                PreparedStatement query = conn.prepareStatement(
                        "INSERT INTO accounts (account_type, balance, is_approved, is_active, owner_username, joint_username)" +
                                "VALUES (?,?,?,?,?,?)");

                query.setString(1,a.accountType);
                query.setBigDecimal(2,a.balance);
                query.setBoolean(3,a.approved);
                query.setBoolean(4,a.active);
                query.setString(5,a.ownerUsername);
                query.setString(6,a.jointUsername);

                query.execute();
            }catch(SQLException e){

                e.printStackTrace();
            }
    }

    //get checking account
    @Override
    public Account retrieve(Customer customer) {
        System.out.println("wrong retrieve() Dum-Dum, go get me some more gum-gum!");
        return null;
    }

    @Override
    public Account retrieve(Customer customer, String type) {
        Connection conn = ConnectionManager.getConnection();
        try{
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM accounts " +
                            "WHERE id = ? ");

            //get correct account id
            switch (type){
                case "checking":
                    query.setInt(1,customer.checkingAcctId);
                    break;
                case "savings":
                    query.setInt(1,customer.savingsAcctId);
                    break;
                case "joint":
                    query.setInt(1,customer.jointAcctId);
                    break;
            }

            ResultSet rs = query.executeQuery();

            //get result from query
            if (rs.next()) {
                Account a = new Account();
                a.id = rs.getInt("id");
                a.accountType = rs.getString("account_type");
                a.balance = rs.getBigDecimal("balance");
                a.approved = rs.getBoolean("is_approved");
                a.active = rs.getBoolean("is_active");
                a.ownerUsername = rs.getString("owner_username");
                a.jointUsername = rs.getString("joint_username");
                return a;
            }
            //query returned nothing
            return null;
        }catch(SQLException e){

            e.printStackTrace();
        }
        return null;
    }


    // Approve/Deny: alters boolean columns 'approved' and 'active'
    // Canceling: alters boolean column 'active'
    // Withdraw/Deposit: alters BigDecimal column 'balance'
    @Override
    public void update(Account a) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE accounts " +
                            "SET account_type = ?, balance = ?, is_approved = ?, is_active = ?, owner_username = ?,  joint_username = ? " +
                            "WHERE id = ?");

            //UPDATE
            query.setString(1, a.accountType);
            query.setBigDecimal(2, a.balance);
            query.setBoolean(3, a.approved);
            query.setBoolean(4, a.active);
            query.setString(5, a.ownerUsername);
            query.setString(6, a.jointUsername);
            //WHERE
            query.setInt(7, a.id);

            query.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Account a) {
        Connection conn = ConnectionManager.getConnection();

        try {
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM accounts " +
                            "WHERE id = ?");

            query.setInt(1, a.id);

            query.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
