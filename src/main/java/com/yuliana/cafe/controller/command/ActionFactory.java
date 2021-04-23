package com.yuliana.cafe.controller.command;

import com.yuliana.cafe.controller.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ActionCommand current = null;
        String action = request.getParameter(RequestParameter.COMMAND);
        if (action == null || action.isEmpty()) {
            response.sendError(400);
        } else {
            try {
                CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException e) {
                response.sendError(400);
            }
        }
        return current;
    }
}