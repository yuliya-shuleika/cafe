package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ToHomeCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.HOME_PAGE;
        return page;
    }
}
