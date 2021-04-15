package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.*;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    int addOrder(int userId, int addressId, int discount, Map<Dish, Integer> dishes,
                 GettingType gettingType, PaymentType paymentType, String comment) throws ServiceException;
    List<Order> findAllOrders() throws ServiceException;
    List<Order> findOrdersByUserId(int userId) throws ServiceException;
    Optional<Order> findOrderById(int orderId) throws ServiceException;
    Optional<Address> findAddressByOrderId(int orderId) throws ServiceException;
}
