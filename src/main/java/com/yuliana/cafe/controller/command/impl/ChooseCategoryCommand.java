package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.DishCategory;
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
    private static final String DISH_CATEGORY_PARAM = "dish_category";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter(DISH_CATEGORY_PARAM);
        DishCategory category = DishCategory.valueOf(categoryName.toUpperCase());
        DishService service = DishServiceImpl.getInstance();
        List<Dish> dishes = null;
        try {
            dishes = service.findDishesByCategory(category);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
