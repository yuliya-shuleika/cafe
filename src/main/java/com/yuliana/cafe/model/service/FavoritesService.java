package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

/**
 * FavoritesServise interface provides operations with favorite dishes list
 *
 * @author Yulia Shuleiko
 */

public interface FavoritesService {

    /**
     * Delete dish from user's favorites list by it's ids.
     *
     * @param dishId id of the dish
     * @param userId id of the user
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deleteDishFromFavorites(int dishId, int userId) throws ServiceException;

    /**
     * Add the dish to user's favorites list by it's ids.
     *
     * @param dishId id of the dish
     * @param userId id of the user
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void addDishToFavorites(int dishId, int userId) throws ServiceException;

    /**
     * Find all the dishes that are in the user's favorites list by user's id.
     *
     * @param userId id of the user
     * @return list of the dishes
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Dish> findUserFavorites(int userId) throws ServiceException;

}
