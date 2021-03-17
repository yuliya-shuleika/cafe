package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.exception.DaoException;

import java.sql.*;
import java.util.Date;

public class OrderDaoImpl implements OrderDao {

    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_ORDER = "INSERT into orders " +
            "(datetime, total, user_id, address_id) VALUES(?, ?, ?, ?)";

    @Override
    public int addOrder(Order order) throws DaoException {
        int orderId;
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)){
            Date datetime = order.getDate();
            Timestamp  timestamp = new Timestamp(datetime.getTime());
            statement.setTimestamp(1, timestamp);
            statement.setDouble(2,order.getTotal());
            statement.setInt(3, order.getUserId());
            statement.setInt(4, order.getAddressId());
            orderId = statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return orderId;
    }

    private Order createOrder(ResultSet orderData) throws SQLException {
        Order order;
        int orderId = orderData.getInt(1);
        Timestamp timestamp = orderData.getTimestamp(2);
        Date datetime = new Date(timestamp.getTime());
        double total = orderData.getDouble(3);
        int userId = orderData.getInt(4);
        int addressId = orderData.getInt(5);
        order= new Order(orderId, datetime,total, userId, addressId);
        return order;
    }
}
