package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface FavoritesService {

    void deleteDishFromFavorites(int dishId, int userId) throws ServiceException;
    void addDishToFavorites(int dishId, int userId) throws ServiceException;
    List<Dish> findUserFavorites(int userId) throws ServiceException;

}
