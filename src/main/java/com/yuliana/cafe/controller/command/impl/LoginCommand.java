package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.CartService;
import com.yuliana.cafe.service.FavoritesService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.CartServiceImpl;
import com.yuliana.cafe.service.impl.FavoritesServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_ERROR = "error";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ERROR_MESSAGE = "error, please try again";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.MENU_PAGE;
        UserService userService = UserServiceImpl.getInstance();
        String login = request.getParameter(PARAM_NAME_EMAIL);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        Optional<User> userOptional = Optional.empty();
        try {
            userOptional = userService.loginUser(login, pass);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            HttpSession session = request.getSession();
            session.setAttribute(ATTRIBUTE_USER, user);
            UserRole role = user.getRole();
            switch (role){
                case USER:
                    page = PagePath.MENU_PAGE;
                    int userId = user.getUserId();;
                    FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
                    List<Dish> favorites = new ArrayList<>();
                    try {
                        favorites = favoritesService.findUserFavorites(userId);
                    } catch (ServiceException e) {
                        logger.log(Level.ERROR, e);
                    }
                    CartService cartService = CartServiceImpl.getInstance();
                    Map<Dish, Integer> cartItems = new HashMap<>();
                    try {
                        cartItems = cartService.findUserItems(userId);
                    } catch (ServiceException e) {
                        logger.log(Level.ERROR, e);
                    }
                    session.setAttribute(AttributeName.CART_ITEMS, cartItems);
                    session.setAttribute(AttributeName.USER_FAVORITES, favorites);
                    break;
                case ADMIN:
                    try {
                        List<User> users = userService.findAllUsers();
                        request.setAttribute(AttributeName.USERS_LIST, users);
                    } catch (ServiceException e) {
                        logger.log(Level.ERROR, e);
                    }
                    page = PagePath.USERS_LIST_PAGE;
            }
        } else {
            request.setAttribute(PARAM_ERROR, ERROR_MESSAGE);
            logger.log(Level.DEBUG, "Wrong password.");
            page = PagePath.MENU_PAGE;
        }
        return page;
    }
}