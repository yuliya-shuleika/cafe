package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.DishService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteFromGuestCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ATTRIBUTE_CART_ITEMS = "cart_items";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";
    private static final String ATTRIBUTE_CART_ITEMS_COUNT = "cart_items_count";
    private static final String PARAM_DISH_ID = "cart_item_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Dish, Integer> cartItems;
        int cartItemsCount;
        DishService service = new DishService();
        HttpSession session = request.getSession();
        String cartItemIdParam = request.getParameter(PARAM_DISH_ID);
        int cartItemId = Integer.parseInt(cartItemIdParam);
        Object cartItemsAttribute = session.getAttribute(ATTRIBUTE_CART_ITEMS);
        if(cartItemsAttribute != null){
            cartItems = (Map<Dish, Integer>) cartItemsAttribute;
        } else {
            cartItems = new HashMap<>();
        }
        Object cartItemsCountAttribute = session.getAttribute(ATTRIBUTE_CART_ITEMS_COUNT);
        if(cartItemsCountAttribute != null){
            cartItemsCount = (Integer) cartItemsCountAttribute;
        } else {
            cartItemsCount = 0;
        }
        Dish cartItem = service.getDishById(cartItemId);
        int count = cartItems.get(cartItem);
        cartItemsCount -= count;
        cartItems.remove(cartItem);
        session.setAttribute(ATTRIBUTE_CART_ITEMS_COUNT, cartItemsCount);
        session.setAttribute(ATTRIBUTE_CART_ITEMS, cartItems);
        List<Dish> menuItems = service.getAllDishes();
        request.setAttribute(ATTRIBUTE_DISHES_LIST, menuItems);
        makeResponse(response, cartItemsCount);
        String page = PagePath.MENU_PAGE;
        return page;
    }

    private void makeResponse(HttpServletResponse response, int cartItemsCount){
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.print(cartItemsCount);
        }catch (IOException e){
            logger.log(Level.WARN, "Error making response with cart items count.");
        }

    }
}
