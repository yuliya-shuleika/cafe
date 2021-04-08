package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DishService {

    Optional<Dish> findDishById(int dishId) throws ServiceException;
    List<Dish> findAllDishes() throws ServiceException;
    List<Dish> findDishesSortedByPrice() throws ServiceException;
    List<Dish> findDishesByCategory(DishCategory category) throws ServiceException;
    List<Dish> findDishesByName(String name) throws ServiceException;
    List<Dish> findDishesSortedByDiscount() throws ServiceException;
    List<Dish> findDishesSortedByName() throws ServiceException;
    void deleteDishById(int dishId) throws ServiceException;
    int addDishToMenu(Map<String, String> dishFields, String pictureName) throws ServiceException;
    void editDish(Map<String, String> dishFields, String pictureName) throws ServiceException;
    List<Dish> findNewDishes() throws ServiceException;

}
