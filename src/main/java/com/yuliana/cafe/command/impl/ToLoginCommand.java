package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;


import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.LOGIN_PAGE;
        return page;
    }
}

