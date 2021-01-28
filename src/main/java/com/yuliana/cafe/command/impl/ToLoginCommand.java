package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.Pages;

import javax.servlet.http.HttpServletRequest;

public class ToLoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.LOGIN_PAGE;
        return page;
    }
}

