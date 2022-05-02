package com.revature.controller;

import com.revature.dao.CustomerDAOImpl;
import com.revature.model.Account;
import com.revature.model.Customer;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class CustomerController {
    CustomerDAOImpl dao;

    public CustomerController(Javalin app){
        dao = new CustomerDAOImpl();

        app.get("/customers/{username}", getHandler);
        app.post("/customers", postHandler);
        app.put("/customers/{username}", putHandler);
        app.delete("/customers/{username}", deleteHandler);
    }

    // create
    public Handler postHandler = ctx ->{

        Customer customer = ctx.bodyAsClass(Customer.class);

        dao.create(customer);

        ctx.status(201);
    };
    // retrieve
    public Handler getHandler = ctx -> {

        String username = ctx.pathParam("username");

        Customer customer = dao.retrieve(username);

        ctx.json(customer);

    };
    // update
    public Handler putHandler = ctx -> {

        Customer customer = ctx.bodyAsClass(Customer.class);

        dao.update(customer);

        ctx.status(200);
    };
    // delete
    public Handler deleteHandler = ctx -> {

        Customer customer = ctx.bodyAsClass(Customer.class);

        dao.delete(customer);

        ctx.status(200);
    };
}
