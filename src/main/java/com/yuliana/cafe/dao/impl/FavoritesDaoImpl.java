package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.FavoritesDao;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yuliana.cafe.dao.creator.EntityCreator.createDish;

public class FavoritesDaoImpl implements FavoritesDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String DELETE_FROM_FAVORITES = "DELETE FROM favorites WHERE dish_id = ? AND user_id = ?";
    private static final String SELECT_ALL_USER_FAVORITES = "SELECT dishes.dish_id, dishes.name, dishes.category, " +
            "dishes.picture_name, dishes.price, dishes.discount_percents, " +
            "dishes.date, dishes.description, dishes.weight " +
            "FROM favorites " +
            "JOIN dishes ON favorites.dish_id = dishes.dish_id " +
            "WHERE favorites.user_id = ? ORDER BY favorites.datetime";
    private static final String INSERT_TO_FAVORITES = "INSERT INTO favorites (datetime, dish_id, user_id) " +
            "VALUES (?, ?, ?)";

    @Override
    public void deleteFromFavorites(int dishId, int userId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FROM_FAVORITES)) {
            statement.setInt(1, dishId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void addToFavorites(int dishId, int userId, Date date) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TO_FAVORITES)) {
            Timestamp timestamp = new Timestamp(date.getTime());
            statement.setTimestamp(1, timestamp);
            statement.setInt(2, dishId);
            statement.setInt(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Dish> findUserFavorites(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        List<Dish> dishes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_FAVORITES)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                dishes.add(dish);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return dishes;
    }
}
