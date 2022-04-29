package com.revature.dao;

public interface DAOInterface <T, ID>{

    public void create(T element);

    public T retrieve(ID id);

    public void update(T element);

    public void delete(T element);
}
