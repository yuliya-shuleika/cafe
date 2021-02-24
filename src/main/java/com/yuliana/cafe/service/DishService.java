package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface DishService {

    Dish getDishById(int dishId) throws ServiceException;
    List<Dish> getAllDishes() throws ServiceException;
    List<Dish> searchDishesByCategory(Category category) throws ServiceException;
    public List<Dish> searchDishesByName(String name) throws ServiceException;

}
