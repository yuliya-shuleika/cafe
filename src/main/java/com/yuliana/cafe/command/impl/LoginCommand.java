package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_ERROR = "error";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ERROR_MESSAGE = "error, please try again";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        UserService service = new UserService();
        String login = request.getParameter(PARAM_NAME_EMAIL);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        User user = service.loginUser(login, pass);
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute(ATTRIBUTE_USER, user);
            page = Pages.HOME_PAGE;
        } else {
            request.setAttribute(PARAM_ERROR, ERROR_MESSAGE);
            logger.log(Level.DEBUG, "Wrong password.");
            page = Pages.LOGIN_PAGE;
        }
        return page;
    }
}