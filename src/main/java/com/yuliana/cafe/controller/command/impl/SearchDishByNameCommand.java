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
import java.util.List;
import java.util.Optional;

public class SearchDishByNameCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(RequestParameter.DISH_NAME);
        DishService service = DishServiceImpl.getInstance();
        try {
            List<Dish> dishes = service.findDishesByName(name);
            request.setAttribute(AttributeName.DISHES_LIST, dishes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
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
        return page;
    }
}
