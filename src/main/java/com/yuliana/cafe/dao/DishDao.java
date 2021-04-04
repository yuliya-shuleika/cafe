package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DishDao extends BaseDao{

    List<Dish> findAllDishes() throws DaoException;
    List<Dish> findDishesByPrice(double min, double max) throws DaoException;
    List<Dish> findDishesByCategory(DishCategory category) throws DaoException;
    List<Dish> findDishesByName(String name) throws DaoException;
    List<Dish> findDishesSortedByPrice() throws DaoException;
    List<Dish> findDishesSortedByDiscount() throws DaoException;
    Optional<Dish> findDishById(int dishId) throws DaoException;
    List<Dish> findDishesSortedByName() throws DaoException;
    void deleteDishById(int dishId) throws DaoException;
    int addDish(Dish dish) throws DaoException;
    List<Dish> findAllDishesSortedByName() throws DaoException;
    void editDish(Dish dish) throws DaoException;
}
