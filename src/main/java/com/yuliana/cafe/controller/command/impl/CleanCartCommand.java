package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.impl.CartServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * Action command that provides deleting all items from user's cart.
 *
 * @author Yulia Shuleiko
 */
public class CleanCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.MENU_PAGE;
        HttpSession session = request.getSession();
        CartServiceImpl cartService = CartServiceImpl.getInstance();
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            int userId = user.getUserId();
            try {
                cartService.deleteAllItems(userId);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                response.sendError(500);
            }
        }
        session.setAttribute(AttributeName.CART_ITEMS, new HashMap<Dish, Integer>());
        return page;
    }
}
