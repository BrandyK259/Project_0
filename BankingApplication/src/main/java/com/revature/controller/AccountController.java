package com.revature.controller;

import com.revature.dao.AccountDAOImpl;
import com.revature.model.Account;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class AccountController {

    AccountDAOImpl dao;

    public AccountController(Javalin app){
        dao = new AccountDAOImpl();

        app.get("/accounts/{username}", getHandler);
        app.post("/accounts", postHandler);
        app.put("/accounts/{username}", putHandler);
        app.delete("/accounts/{username}", deleteHandler);
    }

    // create
    public Handler postHandler = ctx ->{

        Account account = ctx.bodyAsClass(Account.class);

        dao.create(account);

        // As a best practice, responses should have descriptive status codes
        ctx.status(201);
    };
    // retrieve
    public Handler getHandler = ctx -> {

        String username = ctx.pathParam("username");

        ArrayList<Account> accounts = dao.retrieveAll(username);

        for (Account a : accounts) {
            ctx.json(a);
        }

    };
    // update
    public Handler putHandler = ctx -> {

        Account account = ctx.bodyAsClass(Account.class);

        dao.update(account);

        ctx.status(200);
    };
    // delete
    public Handler deleteHandler = ctx -> {

        Account account = ctx.bodyAsClass(Account.class);

        dao.delete(account);

        ctx.status(200);
    };
}
