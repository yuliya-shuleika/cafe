package com.yuliana.cafe.controller.listener;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.entity.Dish;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

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
        Map<Dish, Integer> cartItems = new HashMap<>();
        session.setAttribute(AttributeName.LANGUAGE, LANG_RU);
        session.setAttribute(AttributeName.CART_ITEMS, cartItems);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.log(Level.INFO, "Session destroyed.");
    }

}
