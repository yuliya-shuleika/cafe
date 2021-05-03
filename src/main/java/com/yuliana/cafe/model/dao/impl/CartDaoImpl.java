package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.CartDao;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.entity.DishCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the {@code CartDao} interface.
 *
 * @author Yulia Shuleiko
 */
public class CartDaoImpl implements CartDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final CartDaoImpl INSTANCE = new CartDaoImpl();
    private static final String SELECT_ITEM_COUNT = "SELECT count FROM cart_items " +
            "WHERE dish_id = ? AND user_id = ?";
    private static final String UPDATE_ITEM_COUNT = "UPDATE cart_items SET count = ? " +
            "WHERE dish_id = ? AND user_id = ?";
    private static final String SELECT_ALL_USER_ITEMS = "SELECT dishes.dish_id, dishes.name, dishes.category, " +
            "dishes.picture_name, dishes.price, dishes.discount_percents, " +
            "dishes.date, dishes.description, dishes.weight, cart_items.count " +
            "FROM cart_items JOIN dishes ON cart_items.dish_id = dishes.dish_id " +
            "WHERE cart_items.user_id = ?";
    private static final String ADD_ITEM = "INSERT INTO cart_items (count, user_id, dish_id) VALUES (?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE dish_id = ? AND user_id = ?";

    /**
     * Forbid creation of the new objects of the class.
     */
    private CartDaoImpl(){}

    /**
     * Getter method of the instance of the {@code CartDaoImpl} class.
     *
     * @return the {@code CartDaoImpl} object
     */
    public static CartDaoImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public void addItem(int userId, int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement updateStatement = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(SELECT_ITEM_COUNT)) {
            selectStatement.setInt(1, dishId);
            selectStatement.setInt(2, userId);
            ResultSet result = selectStatement.executeQuery();
            if (result.next()) {
                int itemCount = result.getInt(1);
                updateStatement = connection.prepareStatement(UPDATE_ITEM_COUNT);
                updateStatement.setInt(1, ++itemCount);
            } else {
                updateStatement = connection.prepareStatement(ADD_ITEM);
                updateStatement.setInt(1, 1);
            }
            updateStatement.setInt(2, userId);
            updateStatement.setInt(3, dishId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(updateStatement);
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteItem(int userId, int dishId, int count) throws DaoException {
        Connection connection = pool.getConnection();
        PreparedStatement updateStatement = null;
        try (PreparedStatement selectStatement = connection.prepareStatement(SELECT_ITEM_COUNT)) {
            selectStatement.setInt(1, dishId);
            selectStatement.setInt(2, userId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int itemCount = resultSet.getInt(1);
                if (itemCount > count) {
                    updateStatement = connection.prepareStatement(UPDATE_ITEM_COUNT);
                    updateStatement.setInt(1, itemCount - count);
                    updateStatement.setInt(2, dishId);
                    updateStatement.setInt(3, userId);
                } else {
                    updateStatement = connection.prepareStatement(DELETE_ITEM);
                    updateStatement.setInt(1, dishId);
                    updateStatement.setInt(2, userId);
                }
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(updateStatement);
            pool.releaseConnection(connection);
        }
    }

    @Override
    public Map<Dish, Integer> findUserItems(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        Map<Dish, Integer> cartItems = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_ITEMS)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Dish dish = createDish(resultSet);
                int count = resultSet.getInt(10);
                cartItems.put(dish, count);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return cartItems;
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
