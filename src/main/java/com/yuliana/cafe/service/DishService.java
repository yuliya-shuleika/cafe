package com.yuliana.cafe.service;

import com.yuliana.cafe.dao.DaoException;
import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.dao.impl.DishDaoImpl;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DishService {

    private static final Logger logger = LogManager.getLogger();
    private DishDao dishDao;

    public DishService(){
        dishDao = new DishDaoImpl();
    }

    public Dish getDishById(int dishId){
        Dish dish = null;
        try {
            dish = dishDao.getDishById(dishId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
        return dish;
    }

    public List<Dish> getAllDishes(){
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.getAllDishes();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
        return menuItems;
    }

    public List<Dish> searchDishesByCategory(Category category){
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.findDishesByCategory(category);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
        return menuItems;
    }

    public List<Dish> searchDishesByName(String name){
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.findDishesByName(name);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
        }
        return menuItems;
    }

}
