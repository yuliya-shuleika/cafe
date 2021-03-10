package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.CartDao;
import com.yuliana.cafe.dao.impl.CartDaoImpl;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.CartService;

public class CartServiceImpl implements CartService {

    private static final CartServiceImpl INSTANCE = new CartServiceImpl();
    private CartDao cartDao = new CartDaoImpl();

    private CartServiceImpl(){}

    public static CartServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public void AddItem(int userId, int dishId, int count) throws ServiceException {

    }

    @Override
    public void deleteItem(int userId, int dishId, int count) throws ServiceException{

    }

    @Override
    public void deleteAllItems(int userId) throws ServiceException{
        try {
            cartDao.deleteAllItems(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
