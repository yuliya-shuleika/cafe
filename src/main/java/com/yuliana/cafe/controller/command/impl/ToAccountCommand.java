package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.OrderService;
import com.yuliana.cafe.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ToAccountCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            orders = orderService.findOrdersByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.USER_ORDERS, orders);
        String page = PagePath.ACCOUNT_PAGE;
        return page;
    }
}
