package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.DishCategory;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Action command that provides choosing a category of dishes from menu.
 *
 * @author Yulia Shuleiko
 */
public class ChooseCategoryCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter(RequestParameter.DISH_CATEGORY);
        DishCategory category = DishCategory.valueOf(categoryName.toUpperCase());
        DishService service = DishServiceImpl.getInstance();
        try {
            List<Dish> dishes = service.findDishesByCategory(category);
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
