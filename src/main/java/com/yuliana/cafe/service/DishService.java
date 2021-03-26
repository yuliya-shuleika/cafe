package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface DishService {

    Dish findDishById(int dishId) throws ServiceException;
    List<Dish> findAllDishes() throws ServiceException;
    List<Dish> findDishesSortedByPrice() throws ServiceException;
    List<Dish> findDishesByCategory(Category category) throws ServiceException;
    List<Dish> findDishesByName(String name) throws ServiceException;
    List<Dish> findDishesSortedByDiscount() throws ServiceException;
    List<Dish> findDishesSortedByName() throws ServiceException;
    void deleteDishById(int dishId) throws ServiceException;
}
