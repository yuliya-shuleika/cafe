package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.CartDao;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartDaoImpl implements CartDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_ITEM_COUNT = "SELECT count FROM cart_items WHERE dish_id = ? AND user_id = ?";
    private static final String UPDATE_ITEM_COUNT = "UPDATE cart_items SET count = ? WHERE dish_id = ? AND user_id = ?";
    private static final String SELECT_ALL_USER_ITEMS = "SELECT dishes.dish_id, dishes.name, dishes.category, " +
            "dishes.picture_name, dishes.price, dishes.discount_percents, cart_items.count FROM cart_items JOIN " +
            "dishes ON cart_items.dish_id = dishes.dish_id WHERE user_id = ?";
    private static final String ADD_ITEM = "INSERT INTO cart_items (user_id, dish_id, count) VALUES (?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE dish_id = ? AND user_id = ?";
    private static final String DELETE_ALL_ITEMS = "DELETE FROM cart_items WHERE user_id = ?";

    @Override
    public void addItem(int userId, int dishId, int count) throws DaoException{
        Connection connection = pool.getConnection();
        try{
            PreparedStatement statement = connection.prepareStatement(SELECT_ITEM_COUNT);
            statement.setInt(1, userId);
            statement.setInt(2, dishId);
            ResultSet resultSet = statement.executeQuery();
            statement.executeQuery();
            if(resultSet.next()){
                int itemCount = resultSet.getInt(1);
                statement = connection.prepareStatement(UPDATE_ITEM_COUNT);
                statement.setInt(3, count + itemCount);
            } else {
                statement = connection.prepareStatement(ADD_ITEM);
                statement.setInt(3, count);
            }
            statement.setInt(1,userId);
            statement.setInt(2, dishId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteItem(int userId, int dishId, int count) throws DaoException{
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ITEM)){
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }


    @Override
    public Map<Dish, Integer> findAllUserItems(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        Map<Dish, Integer> cartItems = new HashMap<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_ITEMS)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Dish dish = createDish(resultSet);
                int count = resultSet.getInt(6);
                cartItems.put(dish, count);
            }
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return cartItems;
    }

    @Override
    public void deleteAllItems(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ALL_ITEMS)){
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private Dish createDish(ResultSet dishData) throws SQLException {
        int dishId = dishData.getInt(1);
        String name = dishData.getString(2);
        String dishCategory = dishData.getString(3);
        DishCategory category = DishCategory.valueOf(dishCategory.toUpperCase());
        String pictureName = dishData.getString(4);
        double price = dishData.getDouble(5);
        short discount = dishData.getShort(6);
        Dish dish = new Dish(dishId, name, category, pictureName, price, discount);
        return dish;
    }
}
