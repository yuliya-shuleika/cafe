package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchDishCommand implements ActionCommand {

    private static final String PARAM_DISH_NAME = "dish_name";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(PARAM_DISH_NAME);
        MenuService service = new MenuService();
        List<Dish> dishes = service.searchItemsByName(name);
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.HOME_PAGE;
        return page;
    }
}
