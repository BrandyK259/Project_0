package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    public static String connectionURL = "jdbc:postgresql://salt.db.elephantsql.com:5432/bheufjfa",
            username = "bheufjfa",
            password = "eW0jYYdl-lW0hZ22v3Rrc2I1BYYCtDuI";

    private static Connection connection;

    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connectionURL, username, password);
            }
            return connection;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
