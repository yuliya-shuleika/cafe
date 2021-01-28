package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.Pages;
import com.yuliana.cafe.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements ActionCommand {

    private static final String PARAM_NAME = "name";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        UserService service = new UserService();
        String name = request.getParameter(PARAM_NAME);
        String login = request.getParameter(PARAM_EMAIL);
        String pass = request.getParameter(PARAM_PASSWORD);
        service.registerUser(name, login, pass);
        page = Pages.LOGIN_PAGE;
        return page;
    }
}
