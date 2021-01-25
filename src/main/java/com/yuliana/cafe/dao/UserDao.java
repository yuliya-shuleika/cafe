package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/interpol?serverTimezone=Europe/Minsk&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yuliana245yuliana";
    private static final String LOGIN = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String REGISTER = "INSERT INTO users ( name , email , password , role ) VALUES ( ? , ? , ? , ? )";

    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public UserDao(){
        connect();
    }

    private void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException throwables) {
            logger.error(throwables);
        }
    }

    public void register(User user){
        try(PreparedStatement statement = connection.prepareStatement(REGISTER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public User login(User user){
        try(PreparedStatement statement = connection.prepareStatement(LOGIN)){
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user.setUserId(result.getInt(1));
                user.setName(result.getString(2));
                user.setRole(result.getString(5));
                return user;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        return null;
    }
}