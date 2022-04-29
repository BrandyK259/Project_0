package com.revature.dao;

import com.revature.model.bankUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class bankUserDAOImpl implements DAOInterface<bankUser, String>{
    /* Table Info:
     *
     * Name:
     *          [bank_users]
     * Columns:
     *        PK[username][varchar] not null
     *          [password][varchar] not null
     *          [is_customer][bool] null
     *          [is_employee][bool] null
     *          [is_admin][bool] null
     */

    @Override
    public void create(bankUser u) {

        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO bank_users " +
                            "VALUES (?,?,?,?,?,?)");

            query.setString(1,u.username);
            query.setString(2,u.password);
            query.setBoolean(3,u.isCustomer);
            query.setBoolean(4,u.isEmployee);
            query.setBoolean(5,u.isAdmin);

            query.execute();

        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public bankUser retrieve(String username) {

        Connection conn = ConnectionManager.getConnection();
        try{
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM bank_users " +
                            "WHERE username = ?");

            query.setString(1,username);

            ResultSet rs = query.executeQuery();

            //get result from query
            if (rs.next()) {
                bankUser u = new bankUser();
                u.username = rs.getString("username");
                u.password = rs.getString("password");
                u.isCustomer = rs.getBoolean("is_customer");
                u.isEmployee = rs.getBoolean("is_employee");
                u.isAdmin = rs.getBoolean("is_admin");
                return u;
            }
            //query returned nothing
            return null;
        }catch(SQLException e){

            e.printStackTrace();
        }

         return null;
    }

    @Override
    public void update(bankUser u) {

        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE bank_users " +
                            "SET password = ?, is_customer = ?, is_employee = ?, is_admin = ?" +
                            "WHERE username = ?");

            //UPDATE
            query.setString(1,u.password);
            query.setBoolean(2,u.isCustomer);
            query.setBoolean(3,u.isEmployee);
            query.setBoolean(4,u.isAdmin);
            //WHERE
            query.setString(5,u.username);

            query.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(bankUser u) {

        Connection conn = ConnectionManager.getConnection();

        try {
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM bank_users " +
                            "WHERE username = ?");

            query.setString(1, u.username);

            query.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
