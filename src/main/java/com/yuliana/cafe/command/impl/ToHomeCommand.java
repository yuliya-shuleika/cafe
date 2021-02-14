package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.DishService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToHomeCommand implements ActionCommand {

    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request) {
        DishService dishService = new DishService();
        List<Dish> menuItems = dishService.getAllDishes();
        request.setAttribute(ATTRIBUTE_DISHES_LIST, menuItems);
        String page = PagePath.HOME_PAGE;
        return page;
    }
}
