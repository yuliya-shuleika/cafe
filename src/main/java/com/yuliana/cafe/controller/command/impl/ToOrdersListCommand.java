package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.OrderService;
import com.yuliana.cafe.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToOrdersListCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = OrderServiceImpl.getInstance();
        List<Order> orders;
        try {
            orders = orderService.findAllOrders();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return PagePath.ERROR_500_PAGE;
        }
        request.setAttribute(AttributeName.ORDERS_LIST, orders);
        String page = PagePath.ORDERS_LIST_PAGE;
        return page;
    }
}
