package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.*;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.*;
import com.yuliana.cafe.model.service.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String WRONG_PASSWORD = "wrong_password";
    private static final String ACCOUNT_NOT_EXIST = "account_not_exists";
    private static final String ADDED_TO_BLACKLIST = "added_to_blacklist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        UserService userService = UserServiceImpl.getInstance();
        String email = request.getParameter(RequestParameter.USER_EMAIL);
        boolean emailExists = false;
        try {
            emailExists = userService.findEmail(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (emailExists) {
            Optional<User> userOptional = Optional.empty();
            Map<String, String> loginForm = new HashMap<>();
            loginForm.put(RequestParameter.USER_EMAIL, email);
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            loginForm.put(RequestParameter.USER_PASSWORD, password);
            try {
                userOptional = userService.loginUser(loginForm);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserStatus status = user.getStatus();
                if (status.equals(UserStatus.BLOCKED)) {
                    request.setAttribute(AttributeName.LOGIN_ERROR_MESSAGE, ADDED_TO_BLACKLIST);
                    logger.log(Level.DEBUG, "User have been added to the blacklist.");
                } else {
                    session.setAttribute(AttributeName.USER, user);
                    UserRole role = user.getRole();
                    switch (role) {
                        case USER:
                            int userId = user.getUserId();
                            try {
                                userService.updateStatus(userId, UserStatus.ONLINE);
                            } catch (ServiceException e) {
                                logger.log(Level.ERROR, e);
                            }
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
                            int cartItemsCount = 0;
                            for (int count : cartItems.values()) {
                                cartItemsCount += count;
                            }
                            session.setAttribute(AttributeName.CART_ITEMS_COUNT, cartItemsCount);
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
                            session.setAttribute(AttributeName.CURRENT_PAGE, page);
                    }
                }
            } else {
                request.setAttribute(AttributeName.LOGIN_ERROR_MESSAGE, WRONG_PASSWORD);
                logger.log(Level.DEBUG, "Wrong password.");
            }
        } else {
            request.setAttribute(AttributeName.LOGIN_ERROR_MESSAGE, ACCOUNT_NOT_EXIST);
            logger.log(Level.DEBUG, "Account doesn't exist.");
        }
        switch (page) {
            case PagePath.MENU_PAGE:
                DishService dishService = DishServiceImpl.getInstance();
                try {
                    List<Dish> dishes = dishService.findAllDishes();
                    request.setAttribute(AttributeName.DISHES_LIST, dishes);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.REVIEWS_PAGE:
                ReviewService reviewService = ReviewServiceImpl.getInstance();
                try {
                    Map<Review, User> reviewsWithAuthors = reviewService.findApprovedReviewsWithAuthors();
                    request.setAttribute(AttributeName.REVIEWS_MAP, reviewsWithAuthors);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
        }
        return page;
    }
}