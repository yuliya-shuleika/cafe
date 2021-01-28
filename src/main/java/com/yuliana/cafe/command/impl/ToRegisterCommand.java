package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ToRegisterCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.REGISTER_PAGE;
        return page;
    }
}