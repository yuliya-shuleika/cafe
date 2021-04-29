package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.FavoritesDao;
import com.yuliana.cafe.model.dao.impl.FavoritesDaoImpl;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.FavoritesService;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@code FavoritesService} interface.
 *
 * @author Yulia Shuleiko
 */
public class FavoritesServiceImpl implements FavoritesService {

    private static final FavoritesServiceImpl INSTANCE = new FavoritesServiceImpl();
    private FavoritesDao favoritesDao = FavoritesDaoImpl.getInstance();

    /**
     * Forbid creation of the new objects of the class.
     */
    private FavoritesServiceImpl() {
    }

    /**
     * Getter method of the instance of the {@code FavoritesServiceImpl} class.
     *
     * @return the {@code AddressDaoImpl} object
     */
    public static FavoritesServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void deleteDishFromFavorites(int dishId, int userId) throws ServiceException {
        try {
            favoritesDao.deleteFromFavorites(dishId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addDishToFavorites(int dishId, int userId) throws ServiceException {
        Date date = new Date();
        try {
            favoritesDao.addToFavorites(dishId, userId, date);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Dish> findUserFavorites(int userId) throws ServiceException {
        List<Dish> dishes;
        try {
            dishes = favoritesDao.findUserFavorites(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return dishes;
    }
}
