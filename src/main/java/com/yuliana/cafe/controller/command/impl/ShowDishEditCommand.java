package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Action command that provides showing dish edit form with all necessary information.
 *
 * @author Yulia Shuleiko
 */
public class ShowDishEditCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.DISHES_LIST_PAGE;
        DishService dishService = DishServiceImpl.getInstance();
        String dishIdParam = request.getParameter(RequestParameter.DISH_ID);
        int dishId = Integer.parseInt(dishIdParam);
        Optional<Dish> dishOptional = Optional.empty();
        try {
            dishOptional = dishService.findDishById(dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (dishOptional.isPresent()) {
            Dish dish = dishOptional.get();
            request.setAttribute(AttributeName.SELECTED_DISH, dish);
        }
        try {
            List<Dish> dishes = dishService.findAllDishes();
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
