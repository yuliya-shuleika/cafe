package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface OrderService {
    int addOrder(User user, int addressId, double total, int discount) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
}
