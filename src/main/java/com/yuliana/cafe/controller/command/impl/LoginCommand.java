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
import java.io.IOException;
import java.util.*;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String WRONG_PASSWORD = "wrong_password";
    private static final String ACCOUNT_NOT_EXIST = "account_not_exists";
    private static final String ADDED_TO_BLACKLIST = "added_to_blacklist";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                response.sendError(500);
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
                            prepareUserData(user, session, userService, response);
                            break;
                        case ADMIN:
                            prepareAdminData(userService, request, response);
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
        loadPageData(page, request, response);
        return page;
    }

    /**
     * Prepare necessary data for user's session.
     *
     * @param user the {@code User} object that keeps user's data
     * @param session the {@code HttpSession} object
     * @param userService the {@code UserService} object
     * @param response the {@code HttpServletResponse} object
     * @throws IOException if occurs an error while sending error's code with response
     */
    private void prepareUserData(User user, HttpSession session, UserService userService,
                                 HttpServletResponse response) throws IOException {
        int userId = user.getUserId();
        List<Dish> favorites = new ArrayList<>();
        Map<Dish, Integer> cartItems = new HashMap<>();
        try {
            userService.updateStatus(userId, UserStatus.ONLINE);
            FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
            favorites = favoritesService.findUserFavorites(userId);
            CartService cartService = CartServiceImpl.getInstance();
            cartItems = cartService.findUserItems(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        int cartItemsCount = 0;
        for (int count : cartItems.values()) {
            cartItemsCount += count;
        }
        session.setAttribute(AttributeName.CART_ITEMS_COUNT, cartItemsCount);
        session.setAttribute(AttributeName.CART_ITEMS, cartItems);
        session.setAttribute(AttributeName.USER_FAVORITES, favorites);
    }

    /**
     * Prepare necessary data for user's session.
     *
     * @param userService he {@code UserService} object
     * @param request he {@code HttpServletRequest} object
     * @param response he {@code HttpServletResponse} object
     * @throws IOException if occurs an error while sending error's code with response
     */
    private void prepareAdminData(UserService userService, HttpServletRequest request,
                                  HttpServletResponse response) throws IOException{
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(AttributeName.USERS_LIST, users);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
    }

    /**
     * Load all necessary data to the current page.
     *
     * @param page path of the current page
     * @param request the {@code HttpServletRequest} object
     * @param response the {@code HttpServletResponse} object
     * @throws IOException if occurs an error while sending error's code with response
     */
    private void loadPageData(String page, HttpServletRequest request,
                              HttpServletResponse response) throws IOException{
        switch (page) {
            case PagePath.MENU_PAGE:
                DishService dishService = DishServiceImpl.getInstance();
                try {
                    List<Dish> dishes = dishService.findAllDishes();
                    request.setAttribute(AttributeName.DISHES_LIST, dishes);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    response.sendError(500);
                }
                break;
            case PagePath.REVIEWS_PAGE:
                ReviewService reviewService = ReviewServiceImpl.getInstance();
                try {
                    Map<Review, User> reviewsWithAuthors = reviewService.findApprovedReviewsWithAuthors();
                    request.setAttribute(AttributeName.REVIEWS_MAP, reviewsWithAuthors);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    response.sendError(500);
                }
                break;
        }
    }
}