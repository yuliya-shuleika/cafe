package com.yuliana.cafe.model.dao;

import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface OrderDao defines operations with orders table and the related tables.
 *
 * @author Yulia Shuleiko
 */
public interface OrderDao extends BaseDao {

    /**
     * Add new user's order.
     *
     * @param order the {@code Order} object
     * @param userId id of the user
     * @param addressId id of the address
     * @return id of the added order
     * @throws DaoException is thrown when occurred error with access to database
     */
    int addUserOrder(Order order, int userId, int addressId) throws DaoException;

    /**
     * Add new order.
     *
     * @param order the {@code Order} object
     * @param email the guest's email
     * @param addressId id of the address
     * @return id of the added order
     * @throws DaoException is thrown when occurred error with access to database
     */
    int addGuestOrder(Order order, String email, int addressId) throws DaoException;
    /**
     * Find all orders.
     *
     * @return list of the orders
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Order> findAllOrders() throws DaoException;

    /**
     * Find orders of the user by user's id.
     *
     * @param userId id of the user
     * @return list of the orders
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Order> findOrdersByUserId(int userId) throws DaoException;

    /**
     * Find the order by it's id.
     *
     * @param orderId id of the order
     * @return the {@code Order} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Order> findOrderById(int orderId) throws DaoException;

    /**
     * Find the address that related to the order by id of the order.
     *
     * @param orderId id of the order
     * @return the {@code Address} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Address> findAddressByOrderId(int orderId) throws DaoException;

}
