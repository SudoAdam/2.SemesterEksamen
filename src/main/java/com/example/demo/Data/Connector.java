package com.example.demo.Data;

import java.io.File;
import java.sql.Connection;

public class Connector {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public Connector() {}

    public Connection getConnection() {
        return connection;
    }

}
