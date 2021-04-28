package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.OrderDao;
import com.yuliana.cafe.model.dao.impl.OrderDaoImpl;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.GettingType;
import com.yuliana.cafe.model.entity.PaymentType;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the {@code OrderService} interface.
 *
 * @author Yulia Shuleiko
 */
public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    private OrderDao orderDao = OrderDaoImpl.getInstance();

    /**
     * Forbid creation of the new objects of the class.
     */
    private OrderServiceImpl(){}

    public static OrderServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public int addUserOrder(int userId, int addressId, int discount, Map<Dish, Integer> dishes,
                        GettingType gettingType, PaymentType paymentType, String comment) throws ServiceException {
        Date date = new Date();
        double total = countOrderPrice(dishes, discount);
        int orderId;
        Order order = new Order(date, total, comment, dishes, paymentType, gettingType);
        try {
            orderId = orderDao.addUserOrder(order, userId, addressId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderId;
    }

    @Override
    public int addGuestOrder(String guestEmail, int addressId, int discount,
                             Map<Dish, Integer> dishes, GettingType gettingType,
                             PaymentType paymentType, String comment) throws ServiceException {
        Date date = new Date();
        double total = countOrderPrice(dishes, discount);
        int orderId;
        Order order = new Order(date, total, comment, dishes, paymentType, gettingType);
        try {
            orderId = orderDao.addGuestOrder(order, guestEmail, addressId);
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

    @Override
    public List<Order> findOrdersByUserId(int userId) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws ServiceException {
        Optional<Order> orderOptional;
        try {
            orderOptional = orderDao.findOrderById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderOptional;
    }

    @Override
    public Optional<Address> findAddressByOrderId(int orderId) throws ServiceException {
        Optional<Address> addressOptional;
        try {
            addressOptional = orderDao.findAddressByOrderId(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return addressOptional;
    }

    /**
     * Count total price of the order.
     *
     * @param orderedDishes map of the {@code Dish} objects and integers.
     *                      Key is ordered dish ang value is it's count.
     * @param discount discount percents of the order
     * @return total price of thr order
     */
    private double countOrderPrice(Map<Dish, Integer> orderedDishes, int discount){
        double price = 0.0;
        for (Dish dish : orderedDishes.keySet()) {
            int count = orderedDishes.get(dish);
            price += dish.getPrice() * count;
        }
        if (discount > 0) {
            price = price - price * discount / 100;
        }
        return price;
    }
}
