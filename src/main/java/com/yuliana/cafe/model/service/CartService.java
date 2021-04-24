package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.Map;

/**
 * CartServise interface provides operations with objects related to the cart.
 *
 * @author Yulia Shuleiko
 */
public interface CartService {

    /**
     * Add item tp the cart.
     *
     * @param userId id of the user
     * @param dishId id of the dish
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void addItem(int userId, int dishId) throws ServiceException;

    /**
     * Delete item from cart or increase it's count depend on count of items that must be removed.
     *
     * @param userId id of the user
     * @param dishId id of the dish
     * @param count count of items that must be removed
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deleteItem(int userId, int dishId, int count) throws ServiceException;

    /**
     * Delete all items from user's cart.
     *
     * @param userId id of the user
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deleteAllItems(int userId) throws ServiceException;

    /**
     * Find all user's cart items.
     *
     * @param userId id of the user
     * @return map of the dishes and integers. Dish is the cart item and integer is it's count
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Map<Dish, Integer> findUserItems(int userId) throws ServiceException;
}
