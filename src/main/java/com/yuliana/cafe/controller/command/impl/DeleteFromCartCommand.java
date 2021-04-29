package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
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
 * Action command that provides deleting the dish from user's cart.
 *
 * @author Yulia Shuleiko
 */
public class DeleteFromCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.MENU_PAGE;
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
            HttpSession session = request.getSession();
            String itemsToDeleteParam = request.getParameter(RequestParameter.ITEMS_TO_DELETE);
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
                        response.sendError(500);
                    }
                }
            }
            Object cartItemsAttribute = session.getAttribute(AttributeName.CART_ITEMS);
            Map<Dish, Integer> cartItems = (Map<Dish, Integer>) cartItemsAttribute;
            Dish dish = dishOptional.get();
            int count = cartItems.get(dish);
            if (count == itemsToDelete) {
                cartItems.remove(dish);
            } else {
                int countUpdate = count - itemsToDelete;
                cartItems.replace(dish, countUpdate);
            }
            int cartItemsCount = 0;
            for (int itemCount : cartItems.values()) {
                cartItemsCount += itemCount;
            }
            session.setAttribute(AttributeName.CART_ITEMS, cartItems);
            session.setAttribute(AttributeName.CART_ITEMS_COUNT, cartItemsCount);
        }
        return page;
    }
}
