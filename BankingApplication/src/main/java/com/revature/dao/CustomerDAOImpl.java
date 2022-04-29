package com.revature.dao;

import com.revature.model.Customer;

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

        }catch(SQLException e){

            e.printStackTrace();
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
                return c;
            }

            //query returned nothing
            return null;
        }catch(SQLException e){

            e.printStackTrace();
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

        }catch(SQLException e){
            e.printStackTrace();
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

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
