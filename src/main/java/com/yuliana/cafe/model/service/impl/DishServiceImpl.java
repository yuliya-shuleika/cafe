package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.DishDao;
import com.yuliana.cafe.model.dao.impl.DishDaoImpl;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.validator.DishValidator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Implementation of the {@code DishService} class.
 *
 * @author Yulia Shuleiko
 */
public class DishServiceImpl implements DishService {

    private static final DishServiceImpl INSTANCE = new DishServiceImpl();
    private final DishDao dishDao = DishDaoImpl.getInstance();
    private static final String FIELD_DISH_NAME = "dish_name";
    private static final String FIELD_DISH_CATEGORY = "dish_category";
    private static final String FIELD_DISH_PRICE = "dish_price";
    private static final String FIELD_DISH_DISCOUNT = "dish_discount";
    private static final String FIELD_DISH_DESCRIPTION = "dish_description";
    private static final String FIELD_DISH_WEIGHT = "dish_weight";

    /**
     * Forbid creation of the new objects of the class.
     */
    private DishServiceImpl() {
    }

    public static DishServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Dish> findDishById(int dishId) throws ServiceException {
        Optional<Dish> dishOptional;
        try {
            dishOptional = dishDao.findDishById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishOptional;
    }

    @Override
    public List<Dish> findAllDishes() throws ServiceException {
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
        List<Dish> sortedItems;
        try {
            sortedItems = dishDao.findAllDishesSortedByPrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return sortedItems;
    }

    @Override
    public List<Dish> findDishesByCategory(DishCategory category) throws ServiceException {
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findDishesByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesByName(String name) throws ServiceException {
        List<Dish> menuItems = new ArrayList<>();
        boolean isValid = DishValidator.isValidDishSearch(name);
        if (isValid) {
            try {
                menuItems = dishDao.findDishesByName(name);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesSortedByDiscount() throws ServiceException {
        List<Dish> menuItems;
        try {
            menuItems = dishDao.findAllDishesSortedByDiscount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return menuItems;
    }

    @Override
    public List<Dish> findDishesSortedByName() throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = dishDao.findAllDishesSortedByName();
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
        if (isValid) {
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
        if (isValid) {
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

    @Override
    public List<Dish> findDishesSortedByPopularity() throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = dishDao.findAllDishesSortedByPopularity();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishes;
    }

    /**
     * Create dish from user's input data.
     *
     * @param dishFields map of the strings where
     *                   key is name of the field and value is user's input.
     * @param picture filepath of the picture of the dish
     * @param category the {@code DishCategory} object
     * @return the {@code Dish} object
     */
    private Dish createDish(Map<String, String> dishFields, String picture, DishCategory category) {
        String name = dishFields.get(FIELD_DISH_NAME);
        String dishPrice = dishFields.get(FIELD_DISH_PRICE);
        double price = Double.parseDouble(dishPrice);
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
