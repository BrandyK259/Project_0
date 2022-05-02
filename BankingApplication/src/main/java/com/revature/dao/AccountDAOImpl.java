package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Admin;
import com.revature.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import revature.BankingApplication.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAOImpl implements AccountDAOInterface<Account, String>{
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
    private static final Logger logger = LogManager.getLogger(App.class);

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
            logger.info("A "+a.accountType+"account has just been made for user "+a.ownerUsername+",with a starting balance of "+a.balance+" .");

        }catch(Exception e){
            e.printStackTrace();
            logger.error("An error has occurred whilst creating this account.");
        }
    }

    @Override
    public Account retrieve(String s) {
        return null;
    }

    //get all accounts
    public  ArrayList<Account> retrieveAll(String username) {
        Connection conn = ConnectionManager.getConnection();
        try{
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM accounts " +
                            "WHERE owner_username = ? ");


            query.setString(1,username);

            ResultSet rs = query.executeQuery();

            ArrayList<Account> accounts = new ArrayList<Account>();

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

                accounts.add(a);
                logger.info("A "+a.accountType+"account has just been found for user "+a.ownerUsername+",with a balance of "+a.balance+" .");
                return accounts;
            }
            //query returned nothing
            logger.info("An account for that user has not been found.");
            return null;

        }catch(Exception e) {
            e.printStackTrace();
            logger.error("An error has occurred whilst retrieving this account.");
        }

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

                logger.info("A "+a.accountType+"account has just been found for user "+a.ownerUsername+",with a balance of "+a.balance+" .");
                return a;
            }
            //query returned nothing
            logger.info("An account for that user has not been found.");
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("An error has occurred whilst retrieving this account.");
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
            logger.info("The "+a.accountType+"account for user "+a.ownerUsername+" has been updated.");

        }catch(Exception e){
            e.printStackTrace();
            logger.error("An error has occurred whilst updating account #"+ a.id+".");
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
            logger.info("The "+a.accountType+"account for user "+a.ownerUsername+" has been deleted.");

        } catch(Exception e){
            e.printStackTrace();
            logger.error("An error has occurred whilst deleting account #"+ a.id+".");
        }
    }

}
