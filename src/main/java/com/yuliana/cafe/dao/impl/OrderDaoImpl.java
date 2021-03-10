package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.dao.OrderDao;
import com.yuliana.cafe.entity.Order;

public class OrderDaoImpl implements OrderDao {

    private static final String INSERT_ORDER = "INSERT into orders (datetime, total, user_id, address_id) VALUES(?, ?, ?)"
    private static final String INSERT_ADDRESS = "INSERT into orders () VALUES(?, ?, ?)"

    @Override
    public void addOrder(Order order) {

    }
}
