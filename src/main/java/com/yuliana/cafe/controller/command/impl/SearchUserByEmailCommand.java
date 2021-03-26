package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
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
import java.util.ArrayList;
import java.util.List;

public class SearchUserByEmailCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_PARAM = "email";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter(EMAIL_PARAM);
        UserService userService = UserServiceImpl.getInstance();
        List<User> users = new ArrayList<>();
        try {
            users = userService.findUsersByEmail(email);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.USERS_LIST, users);
        String page = PagePath.USERS_LIST_PAGE;
        return page;
    }
}
