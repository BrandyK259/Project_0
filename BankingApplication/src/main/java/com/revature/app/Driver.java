package com.revature.app;

import com.revature.controller.AccountController;
import com.revature.controller.CustomerController;
import com.revature.controller.EmployeeController;
import com.revature.dao.AccountDAOImpl;
import com.revature.model.Account;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import revature.BankingApplication.App;

import java.math.BigDecimal;

public class Driver {

    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args){
        /* --Console--
         *
         *
         * Hello and Welcome to Gringotts Wizarding Bank!
         *
         * Please navigate to your specialized login page
         * ____________________________________________________________
         * 1. Customer Login
         * 2. Employee Login
         * 3. Bank Administrator Login
         *
         *
         */

        Javalin app = Javalin.create().start(7070);
        logger.trace("Testing logging");
        logger.debug("Testing logging");
        logger.info("Testing logging");
        logger.warn("Testing logging!");
        logger.error("Testing logging");
        logger.fatal("Testing logging");

        AccountController accountController = new AccountController(app);
        CustomerController customerController = new CustomerController(app);
        EmployeeController employeeController = new EmployeeController(app);


    }
}
