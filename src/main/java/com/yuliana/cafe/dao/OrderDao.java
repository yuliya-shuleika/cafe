package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface OrderDao extends BaseDao{

    int addOrder(Order order, int userId, int addressId) throws DaoException;
    List<Order> findAllOrders() throws DaoException;
    List<Order> findOrdersByUserId(int userId) throws DaoException;
    Optional<Order> findOrderById(int orderId) throws DaoException;
    Optional<Address> findAddressByOrderId(int orderId) throws DaoException;

}
