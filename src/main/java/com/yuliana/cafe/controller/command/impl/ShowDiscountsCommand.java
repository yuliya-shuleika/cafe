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

public class ShowDiscountsCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ATTRIBUTE_DISHES_LIST = "dishes_list";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        DishService service = DishServiceImpl.getInstance();
        List<Dish> dishes = null;
        try {
            dishes = service.findDishesSortedByDiscount();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(ATTRIBUTE_DISHES_LIST, dishes);
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
