package com.yuliana.cafe.command.impl;

import com.yuliana.cafe.command.ActionCommand;
import com.yuliana.cafe.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class ToMenuCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.REGISTER_PAGE;
        return page;
    }
}