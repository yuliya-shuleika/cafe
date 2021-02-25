package com.yuliana.cafe.controller.listener;

import com.yuliana.cafe.controller.SessionAttribute;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.Review;;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger();
    private static final String LANG_RU = "ru";

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.log(Level.INFO, "Session created.");
        HttpSession session = se.getSession();
        DishService dishService = new DishServiceImpl();
        ReviewService reviewService = new ReviewServiceImpl();
        List<Dish> menuItems = new ArrayList<>();
        List<Review> reviewsList = new ArrayList<>();
        Map<Dish, Integer> cartItems = new HashMap<>();
        try {
            menuItems = dishService.getAllDishes();
            reviewsList = reviewService.getAllReviews();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        session.setAttribute(SessionAttribute.DISHES_LIST, menuItems);
        session.setAttribute(SessionAttribute.REVIEWS_LIST, reviewsList);
        session.setAttribute(SessionAttribute.LANGUAGE, LANG_RU);
        session.setAttribute(SessionAttribute.CART_ITEMS, cartItems);
        session.setAttribute(SessionAttribute.CART_ITEMS_COUNT, 0);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.log(Level.INFO, "Session destroyed.");
    }

}
