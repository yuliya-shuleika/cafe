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

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_ERROR = "error";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        UserService service = new UserService();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = new String(request.getParameter(PARAM_NAME_PASSWORD).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        User user = service.loginUser(login, pass);
        if(user != null) {
            request.setAttribute("user", user.getName());
            page = "/jsp/main.jsp";
        } else {
            request.setAttribute(PARAM_ERROR, "error, please try again");
            logger.log(Level.DEBUG, "password error.");
            page = "/jsp/login.jsp";
        }
        return page;
    }
}