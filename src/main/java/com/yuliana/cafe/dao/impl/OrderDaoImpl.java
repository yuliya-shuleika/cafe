package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class OrderDaoImpl implements OrderDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_ORDER = "INSERT into orders " +
            "(datetime, total, user_id, address_id) VALUES(?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT order_id, datetime, total, user_id, address_id " +
            "FROM orders";
    private static final String SELECT_ORDER_BY_USER_ID = "SELECT order_id, datetime, total " +
            "FROM orders WHERE user_id = ?";
    private static final String INSERT_ORDERED_DISH = "INSERT INTO ordered_dishes (count, order_id, dish_id) " +
            "VALUES (?, ?, ?)";
    private static final String SELECT_ORDERED_DISHES_BY_ORDER_ID = "SELECT dishes.dish_id, " +
            "dishes.name, dishes.category, dishes.picture_name, dishes.price, " +
            "dishes.discount_percents, ordered_dishes.count, ordered_dishes.order_id " +
            "FROM ordered_dishes JOIN dishes " +
            "ON dishes.dish_id = ordered_dishes.dish_id WHERE ordered_dishes.order_id = ?";

    @Override
    public int addOrder(Order order, int userId, int addressId) throws DaoException {
        int orderId;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)){
            Date datetime = order.getDate();
            Timestamp  timestamp = new Timestamp(datetime.getTime());
            statement.setTimestamp(1, timestamp);
            statement.setDouble(2,order.getTotal());
            statement.setInt(3, userId);
            statement.setInt(4, addressId);
            orderId = statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return orderId;
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = pool.getConnection();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_ORDERS);
            while (result.next()){
                //Order order = createOrder(result, );
                //orders.add(order);
            }
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = pool.getConnection();
        setAutoCommit(connection, false);
        PreparedStatement dishesStatement = null;
        try (PreparedStatement orderStatement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)){
            orderStatement.setInt(1, userId);
            ResultSet orderResult = orderStatement.executeQuery();
            while (orderResult.next()){
                int orderId = orderResult.getInt(1);
                Map<Dish, Integer> dishes = new HashMap<>();
                dishesStatement = connection.prepareStatement(SELECT_ORDERED_DISHES_BY_ORDER_ID);
                dishesStatement.setInt(1, orderId);
                ResultSet dishesResult = dishesStatement.executeQuery();
                while (dishesResult.next()){
                    int count = dishesResult.getInt(7);
                    Dish dish = createDish(dishesResult);
                    dishes.put(dish, count);
                }
                Order order = createOrder(orderResult, dishes);
                orders.add(order);
            }
            commit(connection);
        } catch (SQLException e){
            rollback(connection);
            throw new DaoException(e);
        } finally {
            setAutoCommit(connection, true);
            pool.releaseConnection(connection);
            close(dishesStatement);
        }
        return orders;
    }

    @Override
    public void addOrderedDish(int count, int orderId, int dishId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDERED_DISH)) {
            statement.setInt(1, count);
            statement.setInt(2, orderId);
            statement.setInt(3, dishId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    private Order createOrder(ResultSet orderData, Map<Dish, Integer> orderedDishes) throws SQLException {
        Order order;
        int orderId = orderData.getInt(1);
        Timestamp timestamp = orderData.getTimestamp(2);
        Date datetime = new Date(timestamp.getTime());
        double total = orderData.getDouble(3);
        order= new Order(orderId, datetime,total, orderedDishes);
        return order;
    }

    private Dish createDish(ResultSet dishData) throws SQLException{
        Dish dish;
        int dishId = dishData.getInt(1);
        String name = dishData.getString(2);
        String dishCategory = dishData.getString(3);
        DishCategory category = DishCategory.valueOf(dishCategory.toUpperCase());
        String pictureName = dishData.getString(4);
        double price = dishData.getDouble(5);
        short discount = dishData.getShort(6);
        dish = new Dish(dishId, name, category, pictureName, price, discount);
        return dish;
    }
}
