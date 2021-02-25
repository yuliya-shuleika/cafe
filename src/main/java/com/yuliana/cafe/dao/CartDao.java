package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.Map;

public interface CartDao {

    void addItem(int userId, int dishId, int count) throws DaoException;
    void deleteItem(int userId, int dishId, int count) throws DaoException;
    Map<Dish, Integer> getAllUserItems(int userId) throws DaoException;
}
