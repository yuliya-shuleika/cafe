package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddDishToMenuCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String DISH_NAME_PARAM = "dish_name";
    private static final String DISH_CATEGORY_PARAM = "dish_category";
    private static final String DISH_PICTURE_NAME_PARAM= "dish_picture_name";
    private static final String DISH_PRICE_PARAM = "dish_price";
    private static final String DISH_DISCOUNT_PARAM = "dish_discount";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> addDishFields = new HashMap<>();
        addDishFields.put(DISH_NAME_PARAM, request.getParameter(DISH_NAME_PARAM));
        addDishFields.put(DISH_CATEGORY_PARAM, request.getParameter(DISH_CATEGORY_PARAM));
        addDishFields.put(DISH_PICTURE_NAME_PARAM, request.getParameter(DISH_PICTURE_NAME_PARAM));
        addDishFields.put(DISH_PRICE_PARAM, request.getParameter(DISH_PRICE_PARAM));
        addDishFields.put(DISH_DISCOUNT_PARAM, request.getParameter(DISH_DISCOUNT_PARAM));
        DishService dishService = DishServiceImpl.getInstance();
        String page = PagePath.DISHES_LIST_PAGE;
        return null;
    }
}