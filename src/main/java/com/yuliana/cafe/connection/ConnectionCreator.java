package com.yuliana.cafe.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionCreator {

    private static final Logger logger = LogManager.getLogger();
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yuliana245yuliana";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cafe";
    private static final String DB_TIMEZONE = "?serverTimezone=Europe/Minsk&allowMultiQueries=true";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Couldn't load driver.");
            throw new RuntimeException();
        }
    }

    private ConnectionCreator() {
    }

    static Connection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL+ DB_TIMEZONE, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            logger.log(Level.FATAL, "Couldn't create connection.");
            throw new RuntimeException();
        }
        return connection;
    }
}
