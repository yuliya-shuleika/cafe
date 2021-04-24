package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToUsersListCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> users = userService.findAllUsers();
            request.setAttribute(AttributeName.USERS_LIST, users);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.USERS_LIST_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
