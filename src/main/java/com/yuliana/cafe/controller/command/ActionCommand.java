package com.yuliana.cafe.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
