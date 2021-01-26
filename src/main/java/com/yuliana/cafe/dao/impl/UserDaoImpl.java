package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/interpol?serverTimezone=Europe/Minsk&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yuliana245yuliana";
    private static final String SQL_LOGIN = "SELECT user_id, name, role FROM users WHERE email = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO users ( name , email , password , role ) VALUES ( ? , ? , ? , ? )";

    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public UserDaoImpl(){
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

    public void register(User user, String password){
        try(PreparedStatement statement = connection.prepareStatement(INSERT_USER)){
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, password);
            statement.setString(5, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public User login(String email, String password){
        try(PreparedStatement statement = connection.prepareStatement(SQL_LOGIN)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            User user = new User();
            if(result.next()){
                user.setUserId(result.getInt(1));
                user.setName(result.getString(2));
                user.setRole(result.getString(3));
                user.setEmail(email);
                return user;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        return null;
    }
}
