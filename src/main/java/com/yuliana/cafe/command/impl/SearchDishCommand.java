package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.DishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchDishCommand implements ActionCommand {

    private static final String PARAM_DISH_NAME = "dish_name";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(PARAM_DISH_NAME);
        DishService service = new DishService();
        List<Dish> dishes = service.searchDishesByName(name);
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
