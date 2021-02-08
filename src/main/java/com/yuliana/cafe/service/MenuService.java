package com.yuliana.cafe.service;

import com.yuliana.cafe.dao.DaoException;
import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.dao.impl.DishDaoImpl;
import com.yuliana.cafe.entity.Dish;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MenuService {

    private static final Logger logger = LogManager.getLogger();
    private DishDao dishDao;

    public MenuService(){
        dishDao = new DishDaoImpl();
    }

    public List<Dish> getAllMenuItems(){
        List<Dish> menuItems = new ArrayList<>();
        try {
            menuItems = dishDao.getAllDishes();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return menuItems;
    }
}
