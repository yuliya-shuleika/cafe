package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class AddToGuestCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String DISH_ID_PARAM = "dish_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Dish, Integer> cartItems;
        int cartItemsCount;
        DishService service = DishServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String dishIdParam = request.getParameter(DISH_ID_PARAM);
        int dishId = Integer.parseInt(dishIdParam);
        Object cartItemsAttribute = session.getAttribute(AttributeName.CART_ITEMS);
        cartItems = (Map<Dish, Integer>) cartItemsAttribute;
        Object cartItemsCountAttribute = session.getAttribute(AttributeName.CART_ITEMS_COUNT);
        cartItemsCount = (Integer) cartItemsCountAttribute;
        cartItemsCount++;
        Dish dish = null;
        try {
            dish = service.findDishById(dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(cartItems.containsKey(dish)){
            int count = cartItems.get(dish);
            cartItems.replace(dish, ++count);
        }else {
            cartItems.put(dish, 1);
        }
        session.setAttribute(AttributeName.CART_ITEMS_COUNT, cartItemsCount);
        session.setAttribute(AttributeName.CART_ITEMS, cartItems);
        String page = PagePath.MENU_PAGE;
        return page;
    }

}
