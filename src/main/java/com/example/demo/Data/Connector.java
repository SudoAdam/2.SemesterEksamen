package com.example.demo.Data;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    // BEHAVIOR ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Connection getConnection() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String url = properties.getProperty("url");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }
}
