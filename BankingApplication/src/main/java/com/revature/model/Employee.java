package com.revature.model;

import com.revature.app.EmployeeActions;

public class Employee{
    /* Table Info:
     *
     * Name:
     *          [employees]
     * Columns:
     *        PK[username][varchar] not null
     *          [password][varchar] not null
     *          [first_name][varchar] not null
     *          [last_name][varchar] not null
     *        FK[curr_customer_un][varchar] null
     *        FK[joint_customer_un][varchar] null
     */
    public String username,
        password,
        firstName,
        lastName,
        currCustomerUn,
        jointCustomerUn;

    public Employee(){}

    public Employee(String username, String password, String firstName, String lastName, String currCustomerUn, String jointCustomerUn){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.currCustomerUn = currCustomerUn;
        this.jointCustomerUn = jointCustomerUn;
    }
    public void setFKnull(Employee e){
        e.currCustomerUn = null;
        e.jointCustomerUn = null;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currCustomerUn='" + currCustomerUn + '\'' +
                ", jointCustomerUn='" + jointCustomerUn + '\'' +
                '}';
    }
}
