package com.yuliana.cafe.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface represents one certain action that processes user's request.
 *
 * @author Yulia Shuleiko
 */
public interface ActionCommand {

    /**
     * Processes a request from {@code Controller} and returns the path of the page to be redirected.
     *
     * @param request {@code HttpServletRequest} object
     * @param response @code HttpServletResponse} object
     * @return path of the page to be redirected
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
