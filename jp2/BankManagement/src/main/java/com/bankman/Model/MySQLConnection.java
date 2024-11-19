package com.bankman.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    private static final String HOST = "jdbc:mysql://localhost:4306/bankman";
    private static final String USERNAME = "c2309g";
    private static final String PASSWORD = "c2309g";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (Exception e) {
            return null;
        }
    }
}
