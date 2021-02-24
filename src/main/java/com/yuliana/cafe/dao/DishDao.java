package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;

public interface DishDao {

    List<Dish> getAllDishes() throws DaoException;
    List<Dish> findDishesByPrice(double min, double max) throws DaoException;
    List<Dish> findDishesByCategory(Category category) throws DaoException;
    List<Dish> findDishesByName(String name) throws DaoException;
    List<Dish> getDishesOrderByPrice() throws DaoException;
    Dish getDishById(int dishId) throws DaoException;

}
