package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.FavoritesService;
import com.yuliana.cafe.service.OrderService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.FavoritesServiceImpl;
import com.yuliana.cafe.service.impl.OrderServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Dish> dishes = new ArrayList<>();
        FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
        try {
            dishes = favoritesService.findUserFavorites(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        UserService userService = UserServiceImpl.getInstance();
        Optional<Address> addressOptional = Optional.empty();
        try {
            addressOptional = userService.findUserAddress(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        Address address = null;
        if(addressOptional.isPresent()){
            address = addressOptional.get();
        }
        request.setAttribute(AttributeName.USER_FAVORITES, dishes);
        request.setAttribute(AttributeName.USER_ORDERS, orders);
        request.setAttribute(AttributeName.USER_ADDRESS, address);
        String page = PagePath.ACCOUNT_PAGE;
        return page;
    }
}
