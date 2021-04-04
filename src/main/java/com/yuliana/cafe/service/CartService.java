package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;

public interface CartService {

    void addItem(int userId, int dishId) throws ServiceException;
    void deleteItem(int userId, int dishId, int count) throws ServiceException;
    void deleteAllItems(int userId) throws ServiceException;
    Map<Dish, Integer> findUserItems(int userId) throws ServiceException;
}
