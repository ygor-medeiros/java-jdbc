package com.ygor.javajdbc.factory;

import com.ygor.javajdbc.App;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory instance = null;

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    private ConnectionFactory() {
        Properties proper = new Properties();

        try {
            proper.load(App.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.URL = proper.getProperty("database_url");
        this.USERNAME = proper.getProperty("database_user");
        this.PASSWORD = proper.getProperty("database_password");
    }

    public static synchronized ConnectionFactory getInstance() {
        return (instance == null) ? new ConnectionFactory() : instance;
    }

    public Connection getConnection() {
        try {

            return DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
