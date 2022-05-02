package com.revature.controller;

import com.revature.dao.CustomerDAOImpl;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.model.Customer;
import com.revature.model.Employee;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController {
    EmployeeDAOImpl dao;

    public EmployeeController(Javalin app){
        dao = new EmployeeDAOImpl();

        app.get("/employees/{username}", getHandler);
        app.post("/employees", postHandler);
        app.put("/employees/{username}", putHandler);
        app.delete("/employees/{username}", deleteHandler);
    }

    // create
    public Handler postHandler = ctx ->{

        Employee employee = ctx.bodyAsClass(Employee.class);

        dao.create(employee);

        ctx.status(201);
    };
    // retrieve
    public Handler getHandler = ctx -> {

        String username = ctx.pathParam("username");

        Employee employee = dao.retrieve(username);

        ctx.json(employee);

    };
    // update
    public Handler putHandler = ctx -> {

        Employee employee = ctx.bodyAsClass(Employee.class);

        dao.update(employee);

        ctx.status(200);
    };
    // delete
    public Handler deleteHandler = ctx -> {

        Employee employee = ctx.bodyAsClass(Employee.class);

        dao.delete(employee);

        ctx.status(200);
    };
}