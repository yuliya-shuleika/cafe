package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.FavoritesService;
import com.yuliana.cafe.model.service.impl.FavoritesServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Action command that provides adding the dish to user's favorites list.
 *
 * @author Yulia Shuleiko
 */
public class AddDishToFavoritesCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.MENU_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        String dishIdParam = request.getParameter(RequestParameter.DISH_ID);
        int dishId = Integer.parseInt(dishIdParam);
        FavoritesService favoritesService = FavoritesServiceImpl.getInstance();
        try {
            favoritesService.addDishToFavorites(dishId, userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        return page;
    }
}
