package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ActionCommand {


    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        UserService service = UserServiceImpl.getInstance();
        String name = request.getParameter(PARAM_NAME);
        String login = request.getParameter(PARAM_EMAIL);
        String pass = request.getParameter(PARAM_PASSWORD);
        try {
            service.registerUser(name, login, pass);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        page = PagePath.MENU_PAGE;
        return page;
    }

}
