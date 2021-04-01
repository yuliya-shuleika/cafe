package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int addOrder(int userId, int addressId, int discount,
                 Map<Dish, Integer> dishes) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
    List<Order> findOrdersByUserId(int userId) throws ServiceException;
}
