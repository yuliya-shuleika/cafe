package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.OrderDao;
import com.yuliana.cafe.model.entity.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
public class OrderDaoImplTest {

    private OrderDao orderDao;
    private int orderId;
    private int userId;

    @BeforeTest
    public void init(){
        orderDao = OrderDaoImpl.getInstance();
        orderId = 25;
        userId = 33;
    }

    @Test
    public void findAllOrdersTest() throws DaoException {
        List<Order> orders = orderDao.findAllOrders();
        int size = orders.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findOrderByUserIdTest() throws DaoException{
        List<Order> orders = orderDao.findOrdersByUserId(userId);
        int size = orders.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findOrderByIdTest() throws DaoException{
        Optional<Order> orderOptional = orderDao.findOrderById(orderId);
        Assert.assertNotEquals(orderOptional, Optional.empty());
    }
}
