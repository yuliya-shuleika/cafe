package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.*;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import com.yuliana.cafe.service.impl.PromoCodeServiceImpl;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ChangeLocaleCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String LANG_ATTRIBUTE = "lang";
    private static final String LANG_EN = "en";
    private static final String LANG_RU = "ru";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute(LANG_ATTRIBUTE);
        if (lang.equals(LANG_EN)) {
            session.setAttribute(LANG_ATTRIBUTE, LANG_RU);
        } else {
            session.setAttribute(LANG_ATTRIBUTE, LANG_EN);
        }
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        switch (page){
            case PagePath.MENU_PAGE:
            case PagePath.DISHES_LIST_PAGE:
                DishService dishService = DishServiceImpl.getInstance();
                try {
                    List<Dish> dishes = dishService.findAllDishes();
                    request.setAttribute(AttributeName.DISHES_LIST, dishes);
                } catch (ServiceException e){
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.REVIEWS_PAGE:
                try {
                    List<Review> reviews = reviewService.findReviewsByStatus(ReviewStatus.APPROVED);
                    request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
                } catch (ServiceException e){
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.REVIEWS_LIST_PAGE:
                try {
                    List<Review> reviews = reviewService.findAllReviews();
                    request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
                } catch (ServiceException e){
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.USERS_LIST_PAGE:
                try {
                    UserService userService = UserServiceImpl.getInstance();
                    List<User> users = userService.findAllUsers();
                    request.setAttribute(AttributeName.USERS_LIST, users);
                } catch (ServiceException e){
                    logger.log(Level.ERROR, e);
                }
                break;
            case PagePath.PROMO_CODES_LIST_PAGE:
                try {
                    PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
                    List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
                    request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
                } catch (ServiceException e){
                    logger.log(Level.ERROR, e);
                }
                break;
        }
        return page;
    }
}
