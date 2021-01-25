package com.yuliana.cafe.command;

import com.yuliana.cafe.command.impl.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action);
        }
        return current;
    }
}