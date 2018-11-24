package com.shrey.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;

    public synchronized static Connection getConnection() {

        try {
            if (conn == null) {
                // db parameters
                String url = "jdbc:sqlite:project.db";
                // create a connection to the database
                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  conn;
    }
}

