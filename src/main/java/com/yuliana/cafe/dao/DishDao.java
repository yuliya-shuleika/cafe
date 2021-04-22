package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.DishCategory;
import com.yuliana.cafe.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The interface DishDao defines operations with dishes table
 *
 * @author Yulia Shuleiko
 */
public interface DishDao extends BaseDao {

    /**
     * Find all the dishes.
     *
     * @return list of the all dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findAllDishes() throws DaoException;

    /**
     *
     * @param min
     * @param max
     * @return
     * @throws DaoException
     */
    List<Dish> findDishesByPrice(double min, double max) throws DaoException;

    /**
     * Find the dishes that relate to the certain category.
     *
     * @param category the {@code DishCategory} object
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findDishesByCategory(DishCategory category) throws DaoException;

    /**
     * Find dishes that have certain part of the name. Сase-independent.
     *
     * @param name part of the dish name
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findDishesByName(String name) throws DaoException;

    /**
     * Find all the dishes ordered by price.
     *
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findDishesSortedByPrice() throws DaoException;

    /**
     * Find teh list of the dishes that have dishes with a discount at the start and rest dishes at the end.
     *
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findDishesSortedByDiscount() throws DaoException;

    /**
     * Find the dish by it's id.
     *
     * @param dishId id of the dish
     * @return the {@code Dish} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Dish> findDishById(int dishId) throws DaoException;

    /**
     * Find all the dishes ordered by name.
     *
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findDishesSortedByName() throws DaoException;

    /**
     * Delete the dish by it's id.
     *
     * @param dishId id of the dish
     * @throws DaoException is thrown when occurred error with access to database
     */
    void deleteDishById(int dishId) throws DaoException;

    /**
     * Add new dish.
     *
     * @param dish the {@code Dish} object
     * @return id of the added dish
     * @throws DaoException is thrown when occurred error with access to database
     */
    int addDish(Dish dish) throws DaoException;

    List<Dish> findAllDishesSortedByName() throws DaoException;

    /**
     * Edit the dish.
     *
     * @param dish the {@code Dish} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void editDish(Dish dish) throws DaoException;

    /**
     *
     * @param date
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findNewDishes(Date date) throws DaoException;
}
