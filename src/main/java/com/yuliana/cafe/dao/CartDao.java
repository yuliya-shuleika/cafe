package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;

import java.util.Map;

public interface CartDao {

    void addItem(User user, Dish dish, int count) throws DaoException;
    void deleteItem(User user, Dish dish) throws DaoException;
    Map<Dish, Integer> getAllUserItems(User user) throws DaoException;
    boolean isItemInCart(Dish dish, User user) throws DaoException;

}
