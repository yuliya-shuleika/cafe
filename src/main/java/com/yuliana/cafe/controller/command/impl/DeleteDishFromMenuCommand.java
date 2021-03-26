package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDishFromMenuCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String DISH_ID_PARAM = "dish_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String dishIdParam = request.getParameter(DISH_ID_PARAM);
        int dishId = Integer.parseInt(dishIdParam);
        DishService dishService = DishServiceImpl.getInstance();
        try {
            dishService.deleteDishById(dishId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.DISHES_LIST_PAGE;
        return page;
    }
}
