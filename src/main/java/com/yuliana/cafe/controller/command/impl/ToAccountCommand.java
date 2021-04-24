package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.FavoritesService;
import com.yuliana.cafe.model.service.OrderService;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.FavoritesServiceImpl;
import com.yuliana.cafe.model.service.impl.OrderServiceImpl;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ToAccountCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            List<Order> orders = orderService.findOrdersByUserId(userId);
            request.setAttribute(AttributeName.USER_ORDERS, orders);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
        try {
            List<Dish> dishes = favoritesService.findUserFavorites(userId);
            request.setAttribute(AttributeName.USER_FAVORITES, dishes);
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
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            request.setAttribute(AttributeName.USER_ADDRESS, address);
        }
        String page = PagePath.ACCOUNT_PAGE;
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
