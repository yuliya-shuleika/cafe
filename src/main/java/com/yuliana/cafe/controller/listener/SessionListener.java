package com.yuliana.cafe.controller.listener;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserStatus;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
        } else {
            session.setAttribute(AttributeName.CURRENT_PAGE, PagePath.HOME_PAGE);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.log(Level.INFO, "Session destroyed.");
        HttpSession session = se.getSession();
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            int userId = user.getUserId();
            UserService service = UserServiceImpl.getInstance();
            try {
                service.updateStatus(userId, UserStatus.OFFLINE);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

}
