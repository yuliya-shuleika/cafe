package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class ToUsersListCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = Pages.USERS_LIST_PAGE;
        return page;
    }
}
