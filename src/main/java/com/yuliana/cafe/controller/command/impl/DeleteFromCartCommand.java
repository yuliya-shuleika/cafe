package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.CartService;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.CartServiceImpl;
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
import java.util.Optional;

public class DeleteFromCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String DISH_ID_PARAM = "dish_id";
    private static final String ITEMS_TO_DELETE_PARAM = "items_count";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DishService dishService = DishServiceImpl.getInstance();
        String dishIdParam = request.getParameter(DISH_ID_PARAM);
        int dishId = Integer.parseInt(dishIdParam);
        Optional<Dish> dishOptional = Optional.empty();
        try {
            dishOptional = dishService.findDishById(dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(dishOptional.isPresent()) {
            HttpSession session = request.getSession();
            String itemsToDeleteParam = request.getParameter(ITEMS_TO_DELETE_PARAM);
            int itemsToDelete = Integer.parseInt(itemsToDeleteParam);
            Optional<Object> userOptional = Optional.ofNullable(session.getAttribute(AttributeName.USER));
            if (userOptional.isPresent()) {
                User user = (User) userOptional.get();
                UserRole role = user.getRole();
                if (role.equals(UserRole.USER)) {
                    CartService cartService = CartServiceImpl.getInstance();
                    try {
                        cartService.deleteItem(user.getUserId(), dishId, itemsToDelete);
                    } catch (ServiceException e) {
                        logger.log(Level.ERROR, e);
                    }
                }
            }
            Object cartItemsAttribute = session.getAttribute(AttributeName.CART_ITEMS);
            Map<Dish, Integer> cartItems = (Map<Dish, Integer>) cartItemsAttribute;
            Dish dish = dishOptional.get();
            int count = cartItems.get(dish);
            if(count == itemsToDelete){
                cartItems.remove(dish);
            } else{
                int countUpdate = count - itemsToDelete;
                cartItems.replace(dish, countUpdate);
            }
            session.setAttribute(AttributeName.CART_ITEMS, cartItems);
        } else {
            logger.log(Level.DEBUG, "Dish wasn't found.");
        }
        String page = PagePath.MENU_PAGE;
        return page;
    }
}