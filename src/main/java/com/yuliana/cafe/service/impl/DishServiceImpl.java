package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.dao.impl.DishDaoImpl;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DishServiceImpl implements DishService {

    private DishDao dishDao = new DishDaoImpl();

    public Dish getDishById(int dishId) throws ServiceException {
        Dish dish = null;
        try {
            dish = dishDao.getDishById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dish;
    }

    public List<Dish> getAllDishes() throws ServiceException{
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.getAllDishes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> getDishesSortedByPrice() throws ServiceException {
        List sortedItems = new ArrayList();
        try {
            sortedItems = dishDao.getDishesSortedByPrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return sortedItems;
    }

    public List<Dish> searchDishesByCategory(Category category) throws ServiceException{
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.findDishesByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    public List<Dish> searchDishesByName(String name) throws ServiceException{
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.findDishesByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> getDishesSortedByDiscount() throws ServiceException {
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.getDishesSortedByDiscount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }
}
