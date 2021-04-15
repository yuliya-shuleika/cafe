package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.DishDao;
import com.yuliana.cafe.dao.impl.DishDaoImpl;
import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.validator.DishValidator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DishServiceImpl implements DishService {

    private static final DishServiceImpl INSTANCE = new DishServiceImpl();
    private DishDao dishDao = new DishDaoImpl();
    private static final String FIELD_DISH_NAME = "dish_name";
    private static final String FIELD_DISH_CATEGORY = "dish_category";
    private static final String FIELD_DISH_PRICE = "dish_price";
    private static final String FIELD_DISH_DISCOUNT = "dish_discount";
    private static final String FIELD_DISH_DESCRIPTION = "dish_description";
    private static final String FIELD_DISH_WEIGHT = "dish_weight";

    private DishServiceImpl(){}

    public static DishServiceImpl getInstance(){
        return INSTANCE;
    }

    public Optional<Dish> findDishById(int dishId) throws ServiceException {
        Optional<Dish> dishOptional = Optional.empty();
        try {
            dishOptional = dishDao.findDishById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishOptional;
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

    public List<Dish> findDishesByCategory(DishCategory category) throws ServiceException{
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

    @Override
    public int addDishToMenu(Map<String, String> dishFields, String pictureName) throws ServiceException {
        String category = dishFields.remove(FIELD_DISH_CATEGORY);
        DishCategory dishCategory = DishCategory.valueOf(category.toUpperCase());
        boolean isValid = DishValidator.isValidDishForm(dishFields);
        int dishId = 0;
        if(isValid){
            Dish dish = createDish(dishFields, pictureName, dishCategory);
            try {
                dishId = dishDao.addDish(dish);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return dishId;
    }

    @Override
    public void editDish(Map<String, String> dishFields, String pictureName, int dishId) throws ServiceException {
        String category = dishFields.remove(FIELD_DISH_CATEGORY);
        DishCategory dishCategory = DishCategory.valueOf(category.toUpperCase());
        boolean isValid = DishValidator.isValidDishForm(dishFields);
        if(isValid){
            Dish dish = createDish(dishFields, pictureName, dishCategory);
            dish.setDishId(dishId);
            try {
                dishDao.editDish(dish);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<Dish> findNewDishes() throws ServiceException {
        List<Dish> dishes;
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now().minusDays(30);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        try {
            dishes = dishDao.findNewDishes(date);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishes;
    }

    private Dish createDish(Map<String,String> dishFields, String picture, DishCategory category){
        String name = dishFields.get(FIELD_DISH_NAME);
        String dishPrice = dishFields.get(FIELD_DISH_PRICE);
        Double price = Double.parseDouble(dishPrice);
        String dishDiscount = dishFields.get(FIELD_DISH_DISCOUNT);
        short discount = Short.parseShort(dishDiscount);
        String description = dishFields.get(FIELD_DISH_DESCRIPTION);
        String dishWeight = dishFields.get(FIELD_DISH_WEIGHT);
        short weight = Short.parseShort(dishWeight);
        Date addedDate = new Date();
        Dish dish = new Dish(name, category, picture, price, discount, addedDate, description, weight);
        return dish;
    }
}
