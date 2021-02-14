package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.DishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddToGuestCartCommand implements ActionCommand {

    private static final String ATTRIBUTE_CART_ITEMS = "cart_items";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";
    private static final String PARAM_DISH_ID = "dish_id";

    @Override
    public String execute(HttpServletRequest request) {
        List<Dish> cartItems;
        DishService service = new DishService();
        HttpSession session = request.getSession();
        Object cartItemsAttribute = session.getAttribute(ATTRIBUTE_CART_ITEMS);
        String dishIdParam = request.getParameter(PARAM_DISH_ID);
        int dishId = Integer.parseInt(dishIdParam);
        if(cartItemsAttribute != null){
            cartItems = (List<Dish>) cartItemsAttribute;
        } else {
            cartItems = new ArrayList<>();
        }
        Dish dish = service.getDishById(dishId);
        cartItems.add(dish);
        session.setAttribute(ATTRIBUTE_CART_ITEMS, cartItems);
        List<Dish> menuItems = service.getAllDishes();
        request.setAttribute(ATTRIBUTE_DISHES_LIST, menuItems);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
