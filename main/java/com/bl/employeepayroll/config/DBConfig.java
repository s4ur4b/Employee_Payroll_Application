package com.bl.employeepayroll.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static Connection con = null;

    static {
        String DB_URL = "jdbc:mysql://localhost:3306/payroll_service";
        String DBUser = "root";
        String DBPassword ="root";

        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            con = DriverManager.getConnection( DB_URL,DBUser,DBPassword );
        } catch (ClassNotFoundException e) {
            e.printStackTrace( );
        } catch (SQLException throwables) {
            throwables.printStackTrace( );
        }
    }

    public static Connection getConnection(){
        return con;
    }

}
