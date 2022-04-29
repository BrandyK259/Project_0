package com.revature.dao;

import com.revature.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements DAOInterface <Admin, String>{
    /* Table Info:
     *
     * Name:
     *          [admins]
     * Columns:
     *          [username][varchar] not null
     *          [password][varchar] not null
     *          [first_name][varchar] not null
     *          [last_name][varchar] not null
     *          [curr_customer_un][varchar] null
     *          [joint_customer_un][varchar] null
     */

    @Override
    public void create(Admin a) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO admins " +
                            "VALUES (?,?,?,?,?,?)");

            query.setString(1,a.username);
            query.setString(2,a.password);
            query.setString(3,a.firstName);
            query.setString(4,a.lastName);
            query.setString(5,a.currCustomerUn);
            query.setString(6,a.jointCustomerUn);

            query.execute();

        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public Admin retrieve(String username) {
        Connection conn = ConnectionManager.getConnection();
        try{
            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM admins " +
                            "WHERE username = ?");

            query.setString(1,username);

            ResultSet rs = query.executeQuery();

            //get result from query
            if (rs.next()) {
                Admin a = new Admin();
                a.username = rs.getString("username");
                a.password = rs.getString("password");
                a.firstName = rs.getString("first_name");
                a.lastName = rs.getString("last_name");
                a.currCustomerUn = rs.getString("curr_customer_un");
                a.jointCustomerUn = rs.getString("joint_customer_un");
                return a;
            }
            //query returned nothing
            return null;
        }catch(SQLException e){

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Admin a) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE admins " +
                            "SET password = ?, first_name = ?, last_name = ?, curr_customer_un = ?, joint_customer_un = ? " +
                            "WHERE username = ?");

            //UPDATE
            query.setString(1, a.password);
            query.setString(2, a.firstName);
            query.setString(3, a.lastName);
            query.setString(4, a.currCustomerUn);
            query.setString(5, a.jointCustomerUn);
            //WHERE
            query.setString(6, a.username);

            query.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Admin a) {
        Connection conn = ConnectionManager.getConnection();

        try {
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM admins " +
                            "WHERE username = ?");

            query.setString(1, a.username);

            query.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
