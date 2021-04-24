package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.OrderDao;
import com.yuliana.cafe.model.dao.creator.EntityCreator;
import com.yuliana.cafe.model.entity.*;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.Date;
import java.util.*;

import static com.yuliana.cafe.model.dao.creator.EntityCreator.createAddress;

public class OrderDaoImpl implements OrderDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_ORDER = "INSERT into orders " +
            "(datetime, total, user_id, address_id, comment, getting_type, payment_type) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "SELECT order_id, datetime, total, user_id, address_id, " +
            "comment, getting_type, payment_type FROM orders";
    private static final String SELECT_ORDER_BY_USER_ID = "SELECT order_id, datetime, total, " +
            "comment, getting_type, payment_type " +
            "FROM orders WHERE user_id = ?";
    private static final String SELECT_ORDER_BY_ID = "SELECT order_id, datetime, " +
            "total, comment, getting_type, payment_type " +
            "FROM orders WHERE order_id = ?";
    private static final String INSERT_ORDERED_DISH = "INSERT INTO ordered_dishes (count, order_id, dish_id) " +
            "VALUES (?, ?, ?)";
    private static final String SELECT_ORDERED_DISHES_BY_ORDER_ID = "SELECT dishes.dish_id, " +
            "dishes.name, dishes.category, dishes.picture_name, dishes.price, " +
            "dishes.discount_percents, dishes.date, dishes.description, dishes.weight, " +
            "ordered_dishes.count, ordered_dishes.order_id " +
            "FROM ordered_dishes JOIN dishes " +
            "ON dishes.dish_id = ordered_dishes.dish_id WHERE ordered_dishes.order_id = ?";
    private static final String SELECT_ORDER_ADDRESS = "SELECT addresses.address_id, " +
            "addresses.city, addresses.street, addresses.house, " +
            "addresses.entrance, addresses.floor, addresses.flat " +
            "FROM orders JOIN addresses ON addresses.address_id = orders.address_id " +
            "WHERE orders.order_id = ?";

    @Override
    public int addOrder(Order order, int userId, int addressId) throws DaoException {
        int orderId;
        PreparedStatement dishesStatement = null;
        Connection connection = pool.getConnection();
        setAutoCommit(connection, false);
        try (PreparedStatement orderStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            Date datetime = order.getDate();
            Timestamp timestamp = new Timestamp(datetime.getTime());
            orderStatement.setTimestamp(1, timestamp);
            orderStatement.setDouble(2, order.getTotal());
            orderStatement.setInt(3, userId);
            orderStatement.setInt(4, addressId);
            orderStatement.setString(5, order.getComment());
            String gettingType = order.getGettingType().name();
            orderStatement.setString(6, gettingType.toLowerCase());
            String paymentType = order.getPaymentType().name();
            orderStatement.setString(7, paymentType.toLowerCase());
            orderId = orderStatement.executeUpdate();
            Map<Dish, Integer> orderedDishes = order.getOrderedDishes();
            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
            dishesStatement = connection.prepareStatement(INSERT_ORDERED_DISH);
            for (Dish dish : orderedDishes.keySet()) {
                dishesStatement.setInt(1, orderedDishes.get(dish));
                dishesStatement.setInt(2, orderId);
                dishesStatement.setInt(3, dish.getDishId());
                dishesStatement.executeUpdate();
            }
            commit(connection);
        } catch (SQLException e) {
            rollback(connection);
            throw new DaoException(e);
        } finally {
            setAutoCommit(connection, true);
            pool.releaseConnection(connection);
            close(dishesStatement);
        }
        return orderId;
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = pool.getConnection();
        PreparedStatement dishesStatement = null;
        try (Statement orderStatement = connection.createStatement()) {
            ResultSet orderResult = orderStatement.executeQuery(SELECT_ALL_ORDERS);
            while (orderResult.next()) {
                int orderId = orderResult.getInt(1);
                Map<Dish, Integer> dishes = new HashMap<>();
                dishesStatement = connection.prepareStatement(SELECT_ORDERED_DISHES_BY_ORDER_ID);
                dishesStatement.setInt(1, orderId);
                ResultSet dishesResult = dishesStatement.executeQuery();
                while (dishesResult.next()) {
                    int count = dishesResult.getInt(10);
                    Dish dish = EntityCreator.createDish(dishesResult);
                    dishes.put(dish, count);
                }
                Order order = createOrder(orderResult, dishes);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(dishesStatement);
            pool.releaseConnection(connection);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = pool.getConnection();
        PreparedStatement dishesStatement = null;
        try (PreparedStatement orderStatement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {
            orderStatement.setInt(1, userId);
            ResultSet orderResult = orderStatement.executeQuery();
            while (orderResult.next()) {
                int orderId = orderResult.getInt(1);
                Map<Dish, Integer> dishes = new HashMap<>();
                dishesStatement = connection.prepareStatement(SELECT_ORDERED_DISHES_BY_ORDER_ID);
                dishesStatement.setInt(1, orderId);
                ResultSet dishesResult = dishesStatement.executeQuery();
                while (dishesResult.next()) {
                    int count = dishesResult.getInt(10);
                    Dish dish = EntityCreator.createDish(dishesResult);
                    dishes.put(dish, count);
                }
                Order order = createOrder(orderResult, dishes);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
            close(dishesStatement);
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws DaoException {
        Optional<Order> orderOptional = Optional.empty();
        Connection connection = pool.getConnection();
        PreparedStatement dishesStatement = null;
        try (PreparedStatement orderStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            orderStatement.setInt(1, orderId);
            ResultSet orderResult = orderStatement.executeQuery();
            if (orderResult.next()) {
                Map<Dish, Integer> orderedDishes = new HashMap<>();
                dishesStatement = connection.prepareStatement(SELECT_ORDERED_DISHES_BY_ORDER_ID);
                dishesStatement.setInt(1, orderId);
                ResultSet dishesResult = dishesStatement.executeQuery();
                while (dishesResult.next()) {
                    Dish dish = EntityCreator.createDish(dishesResult);
                    int count = dishesResult.getInt(10);
                    orderedDishes.put(dish, count);
                }
                Order order = createOrder(orderResult, orderedDishes);
                orderOptional = Optional.of(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
            close(dishesStatement);
        }
        return orderOptional;
    }

    @Override
    public Optional<Address> findAddressByOrderId(int orderId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<Address> addressOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_ADDRESS)) {
            statement.setInt(1, orderId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Address address = createAddress(result);
                addressOptional = Optional.of(address);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return addressOptional;
    }

    private Order createOrder(ResultSet orderData, Map<Dish, Integer> orderedDishes) throws SQLException {
        Order order;
        int orderId = orderData.getInt(1);
        Timestamp timestamp = orderData.getTimestamp(2);
        Date datetime = new Date(timestamp.getTime());
        double total = orderData.getDouble(3);
        String comment = orderData.getString(4);
        String gettingType = orderData.getString(5).toUpperCase();
        GettingType getting = GettingType.valueOf(gettingType);
        String paymentType = orderData.getString(6).toUpperCase();
        PaymentType payment = PaymentType.valueOf(paymentType);
        order = new Order(orderId, datetime, total, comment, orderedDishes, payment, getting);
        return order;
    }

}
