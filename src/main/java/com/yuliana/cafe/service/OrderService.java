package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;

public interface OrderService {
    void addOrder(User user, double total, Map<Dish, Integer> orderedDishes, Address address) throws ServiceException;

}
