package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.PagePath;
import com.yuliana.cafe.entity.Category;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChooseCategoryCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_CATEGORIES = "dish_category";
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter(PARAM_CATEGORIES);
        Category category = Category.valueOf(categoryName.toUpperCase());
        DishService service = new DishServiceImpl();
        List<Dish> dishes = null;
        try {
            dishes = service.searchDishesByCategory(category);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
