package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/login.jsp";
        request.getSession().invalidate();
        return page;
    }
}

