package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface FavoritesDao extends BaseDao {

    void deleteFromFavorites(int dishId, int userId) throws DaoException;

    void addToFavorites(int dishId, int userId, Date date) throws DaoException;

    List<Dish> findUserFavorites(int userId) throws DaoException;

}
