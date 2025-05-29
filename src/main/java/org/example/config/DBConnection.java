package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String userName = "postgres";
    private final static String password = "1234";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,userName,password);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
