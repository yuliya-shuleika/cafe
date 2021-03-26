package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
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

public class SearchDishCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_DISH_NAME = "dish_name";
    private static final String ATTRIBUTE_DISHES_LIST = "search_dishes_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(PARAM_DISH_NAME);
        DishService service = DishServiceImpl.getInstance();
        List<Dish> dishes = null;
        try {
            dishes = service.findDishesByName(name);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
