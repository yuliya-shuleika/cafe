package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeUserRoleCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String role = request.getParameter(RequestParameter.USER_ROLE);
        String userIdParam = request.getParameter(RequestParameter.USER_ID);
        int userId = Integer.parseInt(userIdParam);
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.updateRole(userId, role);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.USERS_LIST_PAGE;
        return page;
    }
}
