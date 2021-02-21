package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.service.DishService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChooseCategoryCommand implements ActionCommand {

    private static final String PARAM_CATEGORIES = "dish_category";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter(PARAM_CATEGORIES);
        Category category = Category.valueOf(categoryName.toUpperCase());
        DishService service = new DishService();
        List<Dish> dishes = service.searchDishesByCategory(category);
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
