package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.entity.ReviewStatus;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RegisterCommand implements ActionCommand {


    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private static final String ACCOUNT_EXISTS = "account_exists";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        UserService userService = UserServiceImpl.getInstance();
        String email = request.getParameter(PARAM_EMAIL);
        boolean emailExists = true;
        try {
            emailExists = userService.findEmail(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(!emailExists) {
            String name = request.getParameter(PARAM_NAME);
            String pass = request.getParameter(PARAM_PASSWORD);
            try {
                userService.registerUser(name, email, pass);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        } else {
            request.setAttribute(AttributeName.REGISTER_ERROR_MESSAGE, ACCOUNT_EXISTS);
            logger.log(Level.DEBUG, "Error register user.");
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
                    List<Review> reviews = reviewService.findReviewsByStatus(ReviewStatus.APPROVED);
                    request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
        }
        return page;
    }
}
