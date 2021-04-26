package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.PromoCode;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.PromoCodeService;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import com.yuliana.cafe.model.service.impl.PromoCodeServiceImpl;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Action command that provides changing the language.
 *
 * @author Yulia Shuleiko
 */
public class ChangeLocaleCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(RequestParameter.LANGUAGE);
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.LANGUAGE, language);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        switch (page) {
            case PagePath.MENU_PAGE:
            case PagePath.DISHES_LIST_PAGE:
                DishService dishService = DishServiceImpl.getInstance();
                try {
                    List<Dish> dishes = dishService.findAllDishes();
                    request.setAttribute(AttributeName.DISHES_LIST, dishes);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.REVIEWS_PAGE:
                try {
                    ReviewService reviewService = ReviewServiceImpl.getInstance();
                    Map<Review, User> reviewsWithAuthors = reviewService.findApprovedReviewsWithAuthors();
                    request.setAttribute(AttributeName.REVIEWS_MAP, reviewsWithAuthors);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.REVIEWS_LIST_PAGE:
                try {
                    ReviewService reviewService = ReviewServiceImpl.getInstance();
                    List<Review> reviews = reviewService.findAllReviews();
                    request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.USERS_LIST_PAGE:
                try {
                    UserService userService = UserServiceImpl.getInstance();
                    List<User> users = userService.findAllUsers();
                    request.setAttribute(AttributeName.USERS_LIST, users);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.PROMO_CODES_LIST_PAGE:
                try {
                    PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
                    List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
                    request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                break;
        }
        return page;
    }
}
