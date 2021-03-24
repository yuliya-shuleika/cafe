package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.dao.impl.OrderDaoImpl;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.OrderService;

import java.util.Date;

public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    public static OrderServiceImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public int addOrder(User user, int addressId, double total, int discount) throws ServiceException {
        Date date = new Date();
        double totalPrice = total * discount / 100;
        int orderId = 0;
        Order order = new Order(date, total, user.getUserId(), addressId);
        try {
            orderId = orderDao.addOrder(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderId;
    }
}
