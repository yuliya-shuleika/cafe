package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.dao.impl.OrderDaoImpl;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public void addOrder(User user, double total, Map<Dish, Integer> orderedDishes, Address address) throws ServiceException {
        Date date = new Date();
        Order order = new Order(date, total, user.getUserId(), address.getAddressId());
        try {
            orderDao.addOrder(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
