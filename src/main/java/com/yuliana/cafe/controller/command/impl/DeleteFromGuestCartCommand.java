package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.SessionAttribute;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.PagePath;
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
import java.util.HashMap;
import java.util.Map;

public class DeleteFromGuestCartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_DISH_ID = "dish_id";
    private static final String PARAM_ITEMS_TO_DELETE = "items_count";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<Dish, Integer> cartItems;
        int cartItemsCount;
        DishService service = new DishServiceImpl();
        HttpSession session = request.getSession();
        String cartItemIdParam = request.getParameter(PARAM_DISH_ID);
        int cartItemId = Integer.parseInt(cartItemIdParam);
        String itemsToDeleteParam = request.getParameter(PARAM_ITEMS_TO_DELETE);
        int itemsToDelete = Integer.parseInt(itemsToDeleteParam);
        Object cartItemsAttribute = session.getAttribute(SessionAttribute.CART_ITEMS);
        cartItems = (Map<Dish, Integer>) cartItemsAttribute;
        Object cartItemsCountAttribute = session.getAttribute(SessionAttribute.CART_ITEMS_COUNT);
        cartItemsCount = (Integer) cartItemsCountAttribute;
        Dish cartItem = null;
        try {
            cartItem = service.getDishById(cartItemId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        int count = cartItems.get(cartItem);
        if(count == itemsToDelete){
            cartItems.remove(cartItem);
        } else{
            int countUpdate = count - itemsToDelete;
            cartItems.replace(cartItem, countUpdate);
        }
        cartItemsCount -= itemsToDelete;
        session.setAttribute(SessionAttribute.CART_ITEMS_COUNT, cartItemsCount);
        session.setAttribute(SessionAttribute.CART_ITEMS, cartItems);
        makeResponse(response, cartItemsCount);
        //current page
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
