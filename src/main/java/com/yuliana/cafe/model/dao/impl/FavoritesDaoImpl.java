package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.FavoritesDao;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.entity.DishCategory;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@code FavoritesDao} interface.
 *
 * @author Yulia Shuleiko
 */
public class FavoritesDaoImpl implements FavoritesDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final FavoritesDaoImpl INSTANCE = new FavoritesDaoImpl();
    private static final String DELETE_FROM_FAVORITES = "DELETE FROM favorites " +
            "WHERE dish_id = ? AND user_id = ?";
    private static final String SELECT_ALL_USER_FAVORITES = "SELECT dishes.dish_id, " +
            "dishes.name, dishes.category, dishes.picture_name, dishes.price, " +
            "dishes.discount_percents, dishes.date, dishes.description, dishes.weight " +
            "FROM favorites " +
            "JOIN dishes ON favorites.dish_id = dishes.dish_id " +
            "WHERE favorites.user_id = ? ORDER BY favorites.datetime";
    private static final String INSERT_TO_FAVORITES = "INSERT INTO favorites (datetime, dish_id, user_id) " +
            "VALUES (?, ?, ?)";

    /**
     * Forbid creation of the new objects of the class.
     */
    private FavoritesDaoImpl(){}

    /**
     * Getter method of the instance of the {@code FavoritesDaoImpl} class.
     *
     * @return the {@code FavoritesDaoImpl} object
     */
    public static FavoritesDaoImpl getInstance(){
        return INSTANCE;
    }

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

    /**
     * Create the {@code Dish} object from the {@code ResultSet} object.
     *
     * @param dishData the {@code ResultSet} object
     * @return the {@code Dish} object
     * @throws SQLException if there is an attempt to get data
     * from the {@code ResultSet} object of the wrong datatype
     */
    public static Dish createDish(ResultSet dishData) throws SQLException {
        int dishId = dishData.getInt(1);
        String name = dishData.getString(2);
        String dishCategory = dishData.getString(3);
        DishCategory category = DishCategory.valueOf(dishCategory.toUpperCase());
        String pictureName = dishData.getString(4);
        double price = dishData.getDouble(5);
        short discount = dishData.getShort(6);
        Date addedDate = dishData.getDate(7);
        String description = dishData.getString(8);
        short weight = dishData.getShort(9);
        Dish dish = new Dish(dishId, name, category, pictureName,
                price, discount, addedDate, description, weight);
        return dish;
    }
}

