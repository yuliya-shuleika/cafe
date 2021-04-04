package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.Map;

public interface CartDao extends BaseDao{

    void addItem(int userId, int dishId) throws DaoException;
    void deleteItem(int userId, int dishId, int count) throws DaoException;
    Map<Dish, Integer> findUserItems(int userId) throws DaoException;
    void deleteAllItems(int userId) throws DaoException;
}
