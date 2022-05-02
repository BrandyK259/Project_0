package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Customer;

public interface AccountDAOInterface<T, ID> extends DAOInterface<T, ID>{


    T retrieve(Customer customer, String string);

}
