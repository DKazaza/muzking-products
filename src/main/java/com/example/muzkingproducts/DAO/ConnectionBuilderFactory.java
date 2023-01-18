package com.example.muzkingproducts.DAO;

public class ConnectionBuilderFactory {
    public static ConnectionBuilder getConnectionBuilder() {
        return new SimpleConnectionBuilder();
    }
}
