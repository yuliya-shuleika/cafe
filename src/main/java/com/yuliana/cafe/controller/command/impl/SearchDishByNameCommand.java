package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.DishService;
import com.yuliana.cafe.model.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Action command that provides searching the dish from menu by the part of it's name.
 *
 * @author Yulia Shuleiko
 */
public class SearchDishByNameCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String DISHES_NOT_FOUND_MESSAGE = "dishes_not_found";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(RequestParameter.DISH_NAME);
        DishService dishService = DishServiceImpl.getInstance();
        List<Dish> dishes = new ArrayList<>();
        try {
            dishes = dishService.findDishesByName(name);
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        HttpSession session = request.getSession();
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        String page;
        if(userOptional.isPresent()){
            User user = (User) userOptional.get();
            UserRole role = user.getRole();
            if(role.equals(UserRole.ADMIN)){
                page = PagePath.DISHES_LIST_PAGE;
            } else {
                page = PagePath.MENU_PAGE;
            }
        } else {
            page = PagePath.MENU_PAGE;
        }
        if(page.equals(PagePath.MENU_PAGE) && dishes.isEmpty()){
            try {
                dishes = dishService.findAllDishes();
                request.setAttribute(AttributeName.DISHES_LIST, dishes);
                request.setAttribute(AttributeName.DISHES_NOT_FOUND, DISHES_NOT_FOUND_MESSAGE);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return page;
    }
}
