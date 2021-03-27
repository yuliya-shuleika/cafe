package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.DishService;
import com.yuliana.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SortDishesByNameCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Dish> dishes = new ArrayList<>();
        DishService dishService = DishServiceImpl.getInstance();
        try {
            dishes = dishService.findDishesSortedByName();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.DISHES_LIST, dishes);
        String page = PagePath.DISHES_LIST_PAGE;
        return page;
    }
}