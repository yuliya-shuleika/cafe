package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String USER_ID_PARAM = "user_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdParam = request.getParameter(USER_ID_PARAM);
        int userId = Integer.parseInt(userIdParam);
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.blockUser(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.USERS_LIST_PAGE;
        return page;
    }
}
