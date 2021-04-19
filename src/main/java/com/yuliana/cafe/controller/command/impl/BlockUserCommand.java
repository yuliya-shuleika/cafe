package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.UserStatus;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdParam = request.getParameter(RequestParameter.USER_ID);
        int userId = Integer.parseInt(userIdParam);
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.updateStatus(userId, UserStatus.BLOCKED);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.USERS_LIST_PAGE;
        return page;
    }
}
