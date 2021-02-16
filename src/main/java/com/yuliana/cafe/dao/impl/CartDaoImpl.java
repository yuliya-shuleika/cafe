package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.CartDao;
import com.yuliana.cafe.dao.DaoException;
import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartDaoImpl implements CartDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String SELECT_USER_ITEM = "SELECT dish_id FROM cart_items WHERE dish_id = ? AND user_id = ?";
    private static final String SELECT_ALL_USER_ITEMS = "SELECT dishes.dish_id, dishes.name, dishes.category, " +
            "dishes.picture_name, dishes.price, dishes.discount_price, cart_items.count FROM cart_items JOIN " +
            "dishes ON cart_items.dish_id = dishes.dish_id WHERE user_id = ?";
    private static final String ADD_ITEM = "INSERT INTO cart_items (user_id, dish_id, count) VALUES (?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM cart_items WHERE dish_id = ? AND user_id = ?";

    @Override
    public void addItem(User user, Dish dish, int count) throws DaoException{
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_ITEM)){
            statement.setInt(1, user.getUserId());
            statement.setInt(2, dish.getDishId());
            statement.setInt(3, count);
            statement.executeQuery();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteItem(User user, Dish dish) throws DaoException{
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ITEM)){
            statement.setInt(1, user.getUserId());
            statement.setInt(2, dish.getDishId());
            statement.executeQuery();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }


    @Override
    public Map<Dish, Integer> getAllUserItems(User user) throws DaoException {
        Connection connection = pool.getConnection();
        Map<Dish, Integer> cartItems = new HashMap<>();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_ITEMS)){
            statement.setInt(1, user.getUserId());
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
    public boolean isItemInCart(Dish dish, User user) throws DaoException {
        Connection connection = pool.getConnection();
        boolean result = false;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_USER_ITEM)){
            statement.setInt(1, dish.getDishId());
            statement.setInt(2, user.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result = true;
            }
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return result;
    }

    private Dish createDish(ResultSet dishData) throws DaoException{
        Dish dish;
        try {
            int dishId = dishData.getInt(1);
            String name = dishData.getString(2);
            String dishCategory = dishData.getString(3);
            Category category = Category.valueOf(dishCategory.toUpperCase());
            String pictureName = dishData.getString(4);
            double price = dishData.getDouble(5);
            dish = new Dish(dishId, name, category, pictureName,price);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return dish;
    }
}
