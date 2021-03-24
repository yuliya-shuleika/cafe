package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.dao.UserDao;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SQL_LOGIN = "SELECT user_id, name, role FROM users WHERE email = ? AND password = ?";
    private static final String INSERT_USER = "INSERT INTO users ( name , email , password , role ) VALUES ( ? , ? , ? , ? )";
    private static final String SELECT_ALL_USERS = "SELECT user_id, name, email, role FROM users";

    @Override
    public void register(User user, String password) throws DaoException {
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, password);
            statement.setString(4, user.getRole().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<User> login(String email, String password) throws DaoException{
        Connection connection = pool.getConnection();
        Optional<User> userOptional = Optional.empty();
        User user;
        try(PreparedStatement statement = connection.prepareStatement(SQL_LOGIN)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            user = createUser(result);
            userOptional = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException(e.getMessage());
        }finally {
            pool.releaseConnection(connection);
        }
        return userOptional;
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        Connection connection = pool.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_USERS);
            while (result.next()) {
                User user = createUser(result);
                users.add(user);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return users;
    }

    private User createUser(ResultSet userData) throws SQLException{
        User user;
        int userId = userData.getInt(1);
        String name = userData.getString(2);
        String email = userData.getString(3);
        String role = userData.getString(5);
        UserRole userRole = UserRole.valueOf(role.toUpperCase());
        user = new User(userId, name, email, userRole);
        return user;
    }
}
