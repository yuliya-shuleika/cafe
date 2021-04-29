package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.CartDao;
import com.yuliana.cafe.model.dao.impl.CartDaoImpl;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.CartService;

import java.util.Map;

/**
 * Implementation of the {@code CartService} interface.
 *
 * @author Yulia Shuleiko
 */
public class CartServiceImpl implements CartService {

    private static final CartServiceImpl INSTANCE = new CartServiceImpl();
    private CartDao cartDao = CartDaoImpl.getInstance();

    /**
     * Forbid creation of the new objects of the class.
     */
    private CartServiceImpl() {
    }

    /**
     * Getter method of the instance of the {@code CartServiceImpl} class.
     *
     * @return the {@code AddressDaoImpl} object
     */
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
