package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.dao.impl.OrderDaoImpl;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    public static OrderServiceImpl getInstance() {
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

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }
}
