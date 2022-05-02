package com.revature.dao;

import com.revature.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import revature.BankingApplication.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl implements DAOInterface <Customer, String> {
    /* Table Info:
     *
     * Name:
     *          [customers]
     * Columns:
     *          [username][varchar] not null
     *          [password][varchar] not null
     *          [first_name][varchar] not null
     *          [last_name][varchar] not null
     *          [email][varchar] null
     *          [checking_acct_id][int] null
     *          [savings_acct_id][int] null
     *          [joint_acct_id][int] null
     */

    private static final Logger logger = LogManager.getLogger(App.class);
    @Override
    public void create(Customer c) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO customers " +
                            "VALUES (?,?,?,?,?,?,?,?)");

            query.setString(1,c.username);
            query.setString(2, c.password);
            query.setString(3, c.firstName);
            query.setString(4,c.lastName);
            query.setString(5,c.email);
            query.setInt(6,c.checkingAcctId);
            query.setInt(7, c.savingsAcctId);
            query.setInt(8,c.jointAcctId);

            query.execute();
            logger.info("User information for Customer "+c.firstName+" "+c.lastName+",has been created.");

        }catch(Exception e){

            e.printStackTrace();
            logger.error("An error has occurred whilst creating this Employee's information.");
        }
    }

    @Override
    public Customer retrieve(String username) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM customers " +
                            "WHERE username = ?");

            query.setString(1,username);

            ResultSet rs = query.executeQuery();

            //get result from query
            if (rs.next()) {
                Customer c = new Customer();
                c.username = rs.getString("username");
                c.password = rs.getString("password");
                c.firstName = rs.getString("first_name");
                c.lastName = rs.getString("last_name");
                c.email = rs.getString("email");
                c.checkingAcctId = rs.getInt("checking_acct_id");
                c.savingsAcctId = rs.getInt("savings_acct_id");
                c.jointAcctId = rs.getInt("joint_acct_id");

                logger.info("User information for Customer "+c.firstName+" "+c.lastName+", has been found.");
                return c;
            }

            //query returned nothing
            logger.info("User information for this Customer has not been found.");
            return null;
        }catch(Exception e){

            e.printStackTrace();
            logger.error("An error has occurred whilst retrieving this Customer's information.");
        }

         return null;
    }

    @Override
    public void update(Customer c) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE customers " +
                            "SET password = ?, first_name = ?, last_name = ?, email = ?, checking_acct_id = ?, savings_acct_id = ?, joint_acct_id = ? " +
                            "WHERE username = ?");

            //UPDATE
            query.setString(1, c.password);
            query.setString(2, c.firstName);
            query.setString(3,c.lastName);
            query.setString(4,c.email);
            query.setInt(5,c.checkingAcctId);
            query.setInt(6, c.savingsAcctId);
            query.setInt(7,c.jointAcctId);
            //WHERE
            query.setString(8, c.username);

            query.executeUpdate();
            logger.info("User information for Customer "+c.firstName+" "+c.lastName+", has been updated.");

        }catch(Exception e){
            e.printStackTrace();
            logger.error("An error has occurred whilst updating Customer "+c.firstName+" "+c.lastName+"'s information.");
        }

    }

    @Override
    public void delete(Customer c) {
        Connection conn = ConnectionManager.getConnection();

        try {
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM customers " +
                            "WHERE username = ?");

            query.setString(1, c.username);

            query.execute();
            logger.info("User information for Customer "+c.firstName+" "+c.lastName+", has been deleted.");

        } catch(Exception e){
            e.printStackTrace();
            logger.error("An error has occurred whilst deleting Customer "+c.firstName+" "+c.lastName+"'s information.");
        }
    }
}
