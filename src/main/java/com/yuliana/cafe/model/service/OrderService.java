package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.model.entity.PaymentType;
import com.yuliana.cafe.model.entity.GettingType;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * OrderServise interface provides operations with {@code Order} objects and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface OrderService {

    /**
     * Add new order. Validate the comment field.
     *
     * @param userId id of the user that made order
     * @param addressId id of the user's or cafe's address
     * @param discount discount of the order from the promo code
     * @param dishes map of the {@code Dish} objects and integers.
     *               Key is a dish that was ordered by user.
     *               Values represents a count of ordered dish.
     * @param gettingType the {@code GettingType} object
     * @param paymentType the {@code PaymentType} object
     * @param comment comment of the order
     * @return id of the added order
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    int addOrder(int userId, int addressId, int discount, Map<Dish, Integer> dishes,
                 GettingType gettingType, PaymentType paymentType, String comment) throws ServiceException;

    /**
     * Find all the orders.
     *
     * @return list of the orders
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Order> findAllOrders() throws ServiceException;

    /**
     * Find all user's orders by it's id.
     *
     * @param userId id of the user
     * @return list of the orders
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Order> findOrdersByUserId(int userId) throws ServiceException;

    /**
     * Find the order by it's id.
     *
     * @param orderId id of the order
     * @return the {@code Order} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Order> findOrderById(int orderId) throws ServiceException;

    /**
     * Find the address by order's id.
     *
     * @param orderId id of the order
     * @return the {@code Address} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Address> findAddressByOrderId(int orderId) throws ServiceException;
}
