package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.CartDao;
import com.yuliana.cafe.dao.impl.CartDaoImpl;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.CartService;

import java.util.Map;

public class CartServiceImpl implements CartService {

    private static final CartServiceImpl INSTANCE = new CartServiceImpl();
    private CartDao cartDao = new CartDaoImpl();

    private CartServiceImpl() {
    }

    public static CartServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(int userId, int dishId) throws ServiceException {
        try {
            cartDao.addItem(userId, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteItem(int userId, int dishId, int count) throws ServiceException {
        try {
            cartDao.deleteItem(userId, dishId, count);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllItems(int userId) throws ServiceException {
        try {
            cartDao.deleteAllItems(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Dish, Integer> findUserItems(int userId) throws ServiceException {
        Map<Dish, Integer> cartItems;
        try {
            cartItems = cartDao.findUserItems(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return cartItems;
    }
}
