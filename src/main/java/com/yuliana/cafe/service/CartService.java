package com.yuliana.cafe.service;

import com.yuliana.cafe.exception.ServiceException;

public interface CartService {

    void AddItem(int userId, int dishId, int count) throws ServiceException;
    void deleteItem(int userId, int dishId, int count) throws ServiceException;
    void deleteAllItems(int userId) throws ServiceException;
}
