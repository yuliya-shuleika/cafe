package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.FavoritesService;
import com.yuliana.cafe.service.impl.FavoritesServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFromFavoritesCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String dishIdParam = request.getParameter(RequestParameter.DISH_ID);
        int dish_id = Integer.parseInt(dishIdParam);
        FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        try {
            favoritesService.deleteDishFromFavorites(dish_id, userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.MENU_PAGE;
        return page;
    }
}
