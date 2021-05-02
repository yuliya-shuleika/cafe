package com.yuliana.cafe.controller.command;

import com.yuliana.cafe.controller.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class represents the factory of the {@code ActionCommand} objects.
 *
 * @author Yulia Shuleiko
 */
public class ActionFactory {

    /**
     * Define the current value of the {@code ActionCommand} object
     *
     * @param request {@code HttpServletRequest} object
     * @param response {@code HttpServletResponse} object
     * @return current value of the {@code ActionCommand} object
     * @throws IOException if occurs an error while sending error's code with a response
     */
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