package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Admin;
import com.revature.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements DAOInterface <Employee, String>{
    /* Table Info:
     *
     * Name:
     *          [employees]
     * Columns:
     *          [username][varchar] not null
     *          [password][varchar] not null
     *          [first_name][varchar] not null
     *          [last_name][varchar] not null
     *          [curr_customer_un][varchar] null
     *          [joint_customer_un][varchar] null
     */

    @Override
    public void create(Employee e) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "INSERT INTO employees " +
                            "VALUES (?,?,?,?,?,?)");

            query.setString(1,e.username);
            query.setString(2,e.password);
            query.setString(3,e.firstName);
            query.setString(4,e.lastName);
            query.setString(5,e.currCustomerUn);
            query.setString(6,e.jointCustomerUn);

            query.execute();

        }catch(SQLException x){

            x.printStackTrace();
        }
    }

    @Override
    public Employee retrieve(String username) {
        Connection conn = ConnectionManager.getConnection();
        try{

            PreparedStatement query = conn.prepareStatement(
                    "SELECT * " +
                            "FROM employees " +
                            "WHERE username = ?");

            query.setString(1,username);

            ResultSet rs = query.executeQuery();

            //get result from query
            if (rs.next()) {
                Employee e = new Employee();
                e.username = rs.getString("username");
                e.password = rs.getString("password");
                e.firstName = rs.getString("first_name");
                e.lastName = rs.getString("last_name");
                e.currCustomerUn = rs.getString("curr_customer_un");
                e.jointCustomerUn = rs.getString("joint_customer_un");
                return e;
            }
            //query returned nothing
            return null;
        }catch(SQLException e){

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Employee e) {
        Connection conn = ConnectionManager.getConnection();

        try{
            PreparedStatement query = conn.prepareStatement(
                    "UPDATE employees " +
                            "SET password = ?, first_name = ?, last_name = ?, curr_customer_un = ?, joint_customer_un = ? " +
                            "WHERE username = ?");

            //UPDATE
            query.setString(1, e.password);
            query.setString(2, e.firstName);
            query.setString(3, e.lastName);
            query.setString(4, e.currCustomerUn);
            query.setString(5, e.jointCustomerUn);
            //WHERE
            query.setString(6, e.username);

            query.executeUpdate();

        }catch(SQLException x){
            x.printStackTrace();
        }
    }

    @Override
    public void delete(Employee e) {
        Connection conn = ConnectionManager.getConnection();

        try {
            PreparedStatement query = conn.prepareStatement(
                    "DELETE FROM employees " +
                            "WHERE username = ?");

            query.setString(1, e.username);

            query.execute();

        } catch(SQLException x){
            x.printStackTrace();
        }
    }
}
