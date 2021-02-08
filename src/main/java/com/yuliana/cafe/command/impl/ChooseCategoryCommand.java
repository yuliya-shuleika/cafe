package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.MenuService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChooseCategoryCommand implements ActionCommand {

    private static final String PARAM_CATEGORIES = "categories";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request) {
        String categoryName = request.getParameter(PARAM_CATEGORIES);
        Category category = Category.valueOf(categoryName.toUpperCase());
        MenuService service = new MenuService();
        List<Dish> dishes = service.searchMenuItemsByCategory(category);
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.HOME_PAGE;
        return page;
    }
}
