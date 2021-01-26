package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_ERROR = "error";
    private static final String ERROR_MESSAGE = "error, please try again";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        UserService service = new UserService();
        String login = request.getParameter(PARAM_NAME_EMAIL);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);;
        User user = service.loginUser(login, pass);
        if(user != null) {
            request.setAttribute("user", user.getName());
            page = "/jsp/main.jsp";
        } else {
            request.setAttribute(PARAM_ERROR, ERROR_MESSAGE);
            logger.log(Level.DEBUG, "password error.");
            page = "/jsp/login.jsp";
        }
        return page;
    }
}