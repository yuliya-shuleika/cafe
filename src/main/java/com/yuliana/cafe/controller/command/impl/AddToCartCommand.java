package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.CartService;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.impl.CartServiceImpl;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Action command that provides adding dish to cart.
 *
 * @author Yulia Shuleiko
 */
public class AddToCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        DishService dishService = DishServiceImpl.getInstance();
        String dishIdParam = request.getParameter(RequestParameter.DISH_ID);
        int dishId = Integer.parseInt(dishIdParam);
        Optional<Dish> dishOptional = Optional.empty();
        try {
            dishOptional = dishService.findDishById(dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        if (dishOptional.isPresent()) {
            addDishToUserCart(dishId, session, response);
            Object cartItemsAttribute = session.getAttribute(AttributeName.CART_ITEMS);
            Map<Dish, Integer> cartItems = (Map<Dish, Integer>) cartItemsAttribute;
            Dish dish = dishOptional.get();
            if (cartItems.containsKey(dish)) {
                int count = cartItems.get(dish);
                cartItems.replace(dish, ++count);
            } else {
                cartItems.put(dish, 1);
            }
            int cartItemsCount = 0;
            for (int count : cartItems.values()) {
                cartItemsCount += count;
            }
            session.setAttribute(AttributeName.CART_ITEMS, cartItems);
            session.setAttribute(AttributeName.CART_ITEMS_COUNT, cartItemsCount);
        }
        return page;
    }

    /**
     * Add dish to the user's cart.
     *
     * @param dishId id of the added dish
     * @param session the {@code HttpSession} object
     * @param response the {@code HttpServletResponse} object
     * @throws IOException if occurs an error while sending error's code with response
     */
    private void addDishToUserCart(int dishId, HttpSession session,
                                    HttpServletResponse response) throws IOException {
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            UserRole role = user.getRole();
            if (role.equals(UserRole.USER)) {
                CartService cartService = CartServiceImpl.getInstance();
                try {
                    cartService.addItem(user.getUserId(), dishId);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    response.sendError(500);
                }
            }
        }
    }
}
