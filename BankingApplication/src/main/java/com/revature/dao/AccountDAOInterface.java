package com.revature.dao;

import com.revature.model.Account;

public interface AccountDAOInterface<T, ID> extends DAOInterface<T, ID>{


    public T retrieve(ID id,String string);

}
