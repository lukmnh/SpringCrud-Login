package com.example.demo.tools;

import java.sql.*;

public class DBConnection {

    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/belajar", "root", "AN@021nime20");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error + " + e.getMessage());

        }
        return conn;
    }
}
