package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Dish;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.FavoritesService;
import com.yuliana.cafe.service.impl.FavoritesServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToMenuCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Optional<Object> userOptional = Optional.ofNullable(session.getAttribute(AttributeName.USER));
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            UserRole role = user.getRole();
            if (role.equals(UserRole.USER)) {
                FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
                List<Dish> dishes = new ArrayList<>();
                try {
                    dishes = favoritesService.findUserFavorites(user.getUserId());
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                request.setAttribute(AttributeName.USER_FAVORITES, dishes);
            }
        }
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
