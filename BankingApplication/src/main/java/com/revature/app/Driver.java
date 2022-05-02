package com.revature.app;

import com.revature.controller.AccountController;
import com.revature.controller.CustomerController;
import com.revature.controller.EmployeeController;
import com.revature.dao.AdminDAOImpl;
import com.revature.dao.CustomerDAOImpl;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import io.javalin.Javalin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import revature.BankingApplication.App;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Driver {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){


        Javalin app = Javalin.create().start(7070);

        AccountController accountController = new AccountController(app);
        CustomerController customerController = new CustomerController(app);
        EmployeeController employeeController = new EmployeeController(app);

        int choice = 0;
        //run menu
        while(choice != 4){
            printMenu(" Hello & Welcome to Gringotts Wizarding Bank!",
                    "\r\n",
                    "1. Customer Login",
                    "2. Employee Login",
                    "3. Bank Administrator Login",
                    "4. Exit Banking Application",
                    "\r\n",
                    "Please either navigate to your specialized login page or exit the application: ");

            try{
                choice = sc.nextInt();

                // navigate to user menu depending on user or quit the app
                switch (choice){
                    case 1: customerLogin();
                        break;
                    case 2: employeeLogin();
                        break;
                    case 3: adminLogin();
                        break;
                    case 4: quitApp();
                        break;
                }

            }
            // user has entered something other than a number
            catch(InputMismatchException e){
                System.out.println("Invalid entry, please enter a number between 1 and 4");
                sc.next();
            }
            // some other error has occurred
            catch(Exception e){
                System.out.println("An error has occurred, please try again");
                sc.next();
            }

        }

    }
    public static void printMenu(String... choices){
        for (String choice : choices){
            System.out.println(choice);
        }
    }
    public static void customerLogin(){
        boolean signingIn = true;
        int choice = 0;
        Customer c = new Customer();
        CustomerActions actions = new CustomerActions();
        //run menu
        while(signingIn){

            System.out.println("Please enter your credentials");
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();

            CustomerDAOImpl dao = new CustomerDAOImpl();
            c = dao.retrieve(username,password);

            // customer has been found
            if (c!=null){
                customerMenu(c);
            }
            else{
                System.out.println("Invalid credentials");
                System.out.println("1. Try again");
                System.out.println("2. Sign Up");
                try{
                    choice = sc.nextInt();

                    switch (choice){
                        case 1: ;
                            break;
                        case 2: actions.register(username,password);
                            System.out.println("User account created!");
                            break;
                    }
                }
                // user has entered something other than a number
                catch(InputMismatchException e){
                    System.out.println("Invalid entry, please enter either 1 or 2");
                    sc.next();
                }
                // some other error has occurred
                catch(Exception e){
                    System.out.println("An error has occurred, please try again");
                    sc.next();
                }
            }

        }
    }
    public static void employeeLogin(){
        boolean signingIn = true;
        int choice = 0;
        Employee e = new Employee();

        while(signingIn){

            System.out.println("Please enter your credentials");
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();

            EmployeeDAOImpl dao = new EmployeeDAOImpl();
            e = dao.retrieve(username,password);

            // customer has been found
            if (e!=null){
                employeeMenu(e);
            }
            else{
                System.out.println("Invalid credentials");
                System.out.println("1. Try again");
                System.out.println("2. Quit Application");
                try{
                    choice = sc.nextInt();

                    switch (choice){
                        case 1: ;
                            break;
                        case 2: quitApp();
                            break;
                    }
                }
                // user has entered something other than a number
                catch(InputMismatchException x){
                    System.out.println("Invalid entry, please enter either 1 or 2");
                    sc.next();
                }
                // some other error has occurred
                catch(Exception x){
                    System.out.println("An error has occurred, please try again");
                    sc.next();
                }
            }

        }
    }
    public static void adminLogin(){
        boolean signingIn = true;
        int choice = 0;
        Admin a = new Admin();
        //run menu
        while(signingIn){

            System.out.println("Please enter your credentials");
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();

            AdminDAOImpl dao = new AdminDAOImpl();
            a = dao.retrieve(username,password);

            // customer has been found
            if (a!=null){
                adminMenu(a);
            }
            else{
                System.out.println("Invalid credentials");
                System.out.println("1. Try again");
                System.out.println("2. Quit Application");
                try{
                    choice = sc.nextInt();

                    switch (choice){
                        case 1: ;
                            break;
                        case 2: quitApp();
                            break;
                    }
                }
                // user has entered something other than a number
                catch(InputMismatchException e){
                    System.out.println("Invalid entry, please enter either 1 or 2");
                    sc.next();
                }
                // some other error has occurred
                catch(Exception e){
                    System.out.println("An error has occurred, please try again");
                    sc.next();
                }
            }

        }
    }
    public static void customerMenu(Customer customer){
        int choice = 0;
        CustomerActions actions = new CustomerActions();
        //run menu
        while(choice != 7){
            printMenu("Hello "+customer.firstName+" "+customer.lastName+", what would you like to do today?",
                    "\r\n",
                    "1. Work with an existing account",
                    "2. Apply for a checking account",
                    "3. Apply for a savings account",
                    "4. Apply for a joint account",
                    "5. Exit Application",
                    "\r\n",
                    "Please enter your choice now: ");

            try{
                choice = sc.nextInt();

                // navigate to user menu depending on user or quit the app
                switch (choice){
                    case 1: actions.accountActions(customer);
                        break;
                    case 2: actions.apply("checking",customer.username);
                        break;
                    case 3: actions.apply("savings",customer.username);
                        break;
                    case 4: actions.apply("joint",customer.username);
                        break;
                    case 5: quitApp();
                        break;
                }

            }
            // user has entered something other than a number
            catch(InputMismatchException e){
                System.out.println("Invalid entry, please enter a number between 1 and 7");
                sc.next();
            }
            // some other error has occurred
            catch(Exception e){
                System.out.println("An error has occurred, please try again");
                sc.next();
            }
        }
    }
    public static void employeeMenu(Employee employee){
        int choice = 0;
        boolean lookingCustomer = true;
        Customer c = new Customer();
        EmployeeActions actions = new EmployeeActions();

        while (lookingCustomer){
            System.out.println("Please enter the username of the person who you are helping today");
            System.out.println("Username: ");
            String username = sc.nextLine();

            CustomerDAOImpl dao = new CustomerDAOImpl();
            c = dao.retrieve(username);

            if (c!=null){
                lookingCustomer = false;
                break;
            }
            else{
                System.out.println("User not found");
                System.out.println("1. Try again");
                System.out.println("2. Quit Application");
                try{
                    choice = sc.nextInt();

                    switch (choice){
                        case 1: ;
                            break;
                        case 2: quitApp();
                            break;
                    }
                }
                // user has entered something other than a number
                catch(InputMismatchException e){
                    System.out.println("Invalid entry, please enter either 1 or 2");
                    sc.next();
                }
                // some other error has occurred
                catch(Exception e){
                    System.out.println("An error has occurred, please try again");
                    sc.next();
                }
            }

        }

        //run menu
        while(choice != 6){
            printMenu("Hello "+employee.firstName+" "+employee.lastName+", what would you like to do today?",
                    "\r\n",
                    "1. View customer account information",
                    "2. View customer account balances",
                    "3. View customer person information",
                    "4. Approve all open applications",
                    "5. Deny all open applications",
                    "6. Exit Application",
                    "\r\n",
                    "Please enter your choice now: ");

            try{
                choice = sc.nextInt();

                // navigate to user menu depending on user or quit the app
                switch (choice){
                    case 1: actions.lookUpCustomer(c);
                        break;
                    case 2: actions.lookUpCustomerBalances(c);
                        break;
                    case 3: actions.findCustomer(c);
                        break;
                    case 4: actions.approveApplications(c);
                        break;
                    case 5: actions.denyApplications(c);
                        break;
                    case 6: quitApp();
                        break;
                }

            }
            // user has entered something other than a number
            catch(InputMismatchException e){
                System.out.println("Invalid entry, please enter a number between 1 and 6");
                sc.next();
            }
            // some other error has occurred
            catch(Exception e){
                System.out.println("An error has occurred, please try again");
                sc.next();
            }
        }
    }
    public static void adminMenu(Admin admin){
        int choice = 0;
        boolean lookingCustomer = true;
        Customer c = new Customer();
        AdminActions actions = new AdminActions();

        while (lookingCustomer){
            System.out.println("Please enter the username of the person who you are helping today");
            System.out.println("Username: ");
            String username = sc.nextLine();

            CustomerDAOImpl dao = new CustomerDAOImpl();
            c = dao.retrieve(username);

            if (c!=null){
                lookingCustomer = false;
                break;
            }
            else{
                System.out.println("User not found");
                System.out.println("1. Try again");
                System.out.println("2. Quit Application");
                try{
                    choice = sc.nextInt();

                    switch (choice){
                        case 1: ;
                            break;
                        case 2: quitApp();
                            break;
                    }
                }
                // user has entered something other than a number
                catch(InputMismatchException e){
                    System.out.println("Invalid entry, please enter either 1 or 2");
                    sc.next();
                }
                // some other error has occurred
                catch(Exception e){
                    System.out.println("An error has occurred, please try again");
                    sc.next();
                }
            }

        }

        //run menu
        while(choice != 6){
            printMenu("Hello "+admin.firstName+" "+admin.lastName+", what would you like to do today?",
                    "\r\n",
                    "1. View customer account information",
                    "2. View customer account balances",
                    "3. View customer person information",
                    "4. Approve all open applications",
                    "5. Deny all open applications",
                    "6. Work with an account",
                    "7. Exit Application",
                    "\r\n",
                    "Please enter your choice now: ");

            try{
                choice = sc.nextInt();

                // navigate to user menu depending on user or quit the app
                switch (choice){
                    case 1: actions.lookUpCustomer(c);
                        break;
                    case 2: actions.lookUpCustomerBalances(c);
                        break;
                    case 3: actions.findCustomer(c);
                        break;
                    case 4: actions.approveApplications(c);
                        break;
                    case 5: actions.denyApplications(c);
                        break;
                    case 6: actions.accountActions(c);
                        break;
                    case 7: quitApp();
                        break;
                }

            }
            // user has entered something other than a number
            catch(InputMismatchException e){
                System.out.println("Invalid entry, please enter a number between 1 and 6");
                sc.next();
            }
            // some other error has occurred
            catch(Exception e){
                System.out.println("An error has occurred, please try again");
                sc.next();
            }
        }
    }
    public static void quitApp(){
        // Close scanner
        sc.close();

        // Clear screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Display exit message
        System.out.println("Exiting application . . . ");
        System.out.println(". . .");
        System.out.println(". . .");

        // Close application
        exit(0);
    }
}
