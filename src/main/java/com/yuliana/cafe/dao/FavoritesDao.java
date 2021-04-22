package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.Date;
import java.util.List;

/**
 * The interface FavoritesDao defines operations with favorites table and the related tables.
 *
 * @author Yulia Shuleiko
 */
public interface FavoritesDao extends BaseDao {

    /**
     * Delete dish from favorites list of the user by it's ids.
     *
     * @param dishId id of the dish
     * @param userId id of the user
     * @throws DaoException is thrown when occurred error with access to database
     */
    void deleteFromFavorites(int dishId, int userId) throws DaoException;

    /**
     * Add dish to the favorites of the user by it's ids.
     *
     * @param dishId id of the dish
     * @param userId id of the user
     * @param date the {@code User} object. Current date
     * @throws DaoException is thrown when occurred error with access to database
     */
    void addToFavorites(int dishId, int userId, Date date) throws DaoException;

    /**
     * Find dishes that are in user's favorites list by user id.
     *
     * @param userId id of the user
     * @return list of the dishes
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Dish> findUserFavorites(int userId) throws DaoException;

}
