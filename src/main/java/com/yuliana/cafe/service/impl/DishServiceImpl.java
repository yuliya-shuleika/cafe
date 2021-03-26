package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.dao.impl.DishDaoImpl;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;

import java.util.List;

public class DishServiceImpl implements DishService {

    private static final DishServiceImpl INSTANCE = new DishServiceImpl();
    private DishDao dishDao = new DishDaoImpl();

    private DishServiceImpl(){}

    public static DishServiceImpl getInstance(){
        return INSTANCE;
    }

    public Dish findDishById(int dishId) throws ServiceException {
        Dish dish;
        try {
            dish = dishDao.findDishById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dish;
    }

    public List<Dish> findAllDishes() throws ServiceException{
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findAllDishes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesSortedByPrice() throws ServiceException {
        List sortedItems;
        try {
            sortedItems = dishDao.findDishesSortedByPrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return sortedItems;
    }

    public List<Dish> findDishesByCategory(Category category) throws ServiceException{
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findDishesByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    public List<Dish> findDishesByName(String name) throws ServiceException{
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findDishesByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesSortedByDiscount() throws ServiceException {
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findDishesSortedByDiscount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesSortedByName() throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = dishDao.findDishesSortedByName();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishes;
    }

    @Override
    public void deleteDishById(int dishId) throws ServiceException {
        try {
            dishDao.deleteDishById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
