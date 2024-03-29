package com.yuliana.cafe.model.dao;

import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.Map;

/**
 * The interface CartDao defines operations with cart_items table
 *
 * @author Yulia Shuleiko
 */
public interface CartDao extends BaseDao {

    /**
     * Add item to the user's cart.
     *
     * @param userId id of the user
     * @param dishId id of the dish
     * @throws DaoException if occurred an error with access to database
     */
    void addItem(int userId, int dishId) throws DaoException;

    /**
     * Delete item from the user's cart or increase it's count depends on count of items to delete.
     *
     * @param userId id of the user
     * @param dishId id of the dish
     * @param count count of items to delete
     * @throws DaoException if occurred an error with access to database
     */
    void deleteItem(int userId, int dishId, int count) throws DaoException;

    /**
     * Find items in the cart of the certain user by user's id.
     *
     * @param userId id of the user
     * @return map of the dishes and integers. Dish is the cart item and integer is it's count
     * @throws DaoException iif occurred an error with access to database
     */
    Map<Dish, Integer> findUserItems(int userId) throws DaoException;
}
