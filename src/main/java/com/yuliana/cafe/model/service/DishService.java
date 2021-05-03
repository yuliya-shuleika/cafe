package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * OrderServise interface provides operations with {@code Dish} objects and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface DishService {

    /**
     * Find dish by it's id.
     *
     * @param dishId id of the dish
     * @return the {@code Dish} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Dish> findDishById(int dishId) throws ServiceException;

    /**
     * Find all rhe dishes.
     *
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findAllDishes() throws ServiceException;

    /**
     * Find all the dishes sorted by price.
     *
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesSortedByPrice() throws ServiceException;

    /**
     * Find dishes by the certain category like sushi, soup, noodles ect.
     *
     * @param category the {@code DishCategory}
     * @return list if the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesByCategory(DishCategory category) throws ServiceException;

    /**
     * Find the dishes by it's name part. Validate the input.
     *
     * @param name part of the name of the dish
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesByName(String name) throws ServiceException;

    /**
     * Find the list of the dishes that have dishes with a discount at the start and rest dishes at the end.
     *
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesSortedByDiscount() throws ServiceException;

    /**
     * Find all the dishes sorted by name.
     *
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesSortedByName() throws ServiceException;

    /**
     * Delete the dish by it's id.
     *
     * @param dishId id of the dish
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deleteDishById(int dishId) throws ServiceException;

    /**
     *
     * @param dishFields map of the string.
     *                   The key represents field of the form and the value is the user's input
     * @param pictureName name of the file path of the dish picture
     * @return id of the added dish
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    int addDishToMenu(Map<String, String> dishFields, String pictureName) throws ServiceException;

    /**
     *
     * @param dishFields map of the string.
     *                   The key represents field of the form and the value is the user's input
     * @param pictureName name of the file path of the dish picture
     * @param dishId id of the dish
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void editDish(Map<String, String> dishFields, String pictureName, int dishId) throws ServiceException;

    /**
     * Find all the dishes sorted by it's popularity.
     *
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findDishesSortedByPopularity() throws ServiceException;

}
