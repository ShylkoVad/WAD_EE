package by.teachmeskills.hw_12052023.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils { //подключение к базе
    private static final String dbUrl = "jdbc:mysql://localhost:3306/payment_system_db";
    private static final String dbUsername = "root";
    private static final String dbPassword = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}