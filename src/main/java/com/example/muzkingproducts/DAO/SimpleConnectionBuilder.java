package com.example.muzkingproducts.DAO;

import com.example.muzkingproducts.GlobalConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SimpleConnectionBuilder implements ConnectionBuilder {
    public SimpleConnectionBuilder() {
       try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/muzking";
        String login = "postgres";
        String password = "testSQL!";
        return DriverManager.getConnection(url, login, password);
    }
    /* @Override
    public Connection getConnection() throws SQLException {
        String url = GlobalConfig.getProperty("db.url");
        String login = GlobalConfig.getProperty("db.login");
        String password = GlobalConfig.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    } */
}
