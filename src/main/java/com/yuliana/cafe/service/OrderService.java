package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

public interface OrderService {
    int addOrder(User user, int addressId, double total, int discount) throws ServiceException;

}
