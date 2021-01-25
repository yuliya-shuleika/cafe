package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class OpenRegisterCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "/jsp/register.jsp";
        request.getSession().invalidate();
        return page;
    }
}