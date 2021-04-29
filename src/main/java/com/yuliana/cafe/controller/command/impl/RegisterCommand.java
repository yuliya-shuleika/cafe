package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegisterCommand implements ActionCommand {


    private static final Logger logger = LogManager.getLogger();
    private static final String ACCOUNT_EXISTS = "account_exists";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        UserService userService = UserServiceImpl.getInstance();
        String email = request.getParameter(RequestParameter.USER_EMAIL);
        boolean emailExists = true;
        try {
            emailExists = userService.findEmail(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        if (!emailExists) {
            String name = request.getParameter(RequestParameter.USER_NAME);
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            try {
                userService.registerUser(name, email, password);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                response.sendError(500);
            }
        } else {
            request.setAttribute(AttributeName.REGISTER_ERROR_MESSAGE, ACCOUNT_EXISTS);
            logger.log(Level.DEBUG, "Error register user.");
        }
        loadPageData(page, request, response);
        return page;
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
                              HttpServletResponse response) throws IOException {
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
