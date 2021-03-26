package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;


public interface OrderDao {
    int addOrder(Order order) throws DaoException;
    List<Order> findAllOrders() throws DaoException;
}
