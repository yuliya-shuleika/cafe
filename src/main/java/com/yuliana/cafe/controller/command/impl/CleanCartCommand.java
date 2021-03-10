package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.impl.CartServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CleanCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        CartServiceImpl cartService = CartServiceImpl.getInstance();
        Map<Dish, Integer> cartItems = (Map<Dish, Integer>) session.getAttribute(AttributeName.CART_ITEMS);
        cartItems.clear();
        if(user != null){
            try {
                cartService.deleteAllItems(user.getUserId());
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        }
        session.setAttribute(AttributeName.CART_ITEMS, cartItems);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
