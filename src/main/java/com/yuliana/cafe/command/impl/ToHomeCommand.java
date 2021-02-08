package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ToHomeCommand implements ActionCommand {

    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request) {
        MenuService menuService = new MenuService();
        List<Dish> menuItems = menuService.getAllMenuItems();
        request.setAttribute(ATTRIBUTE_DISHES_LIST, menuItems);
        String page = Pages.HOME_PAGE;
        return page;
    }
}
