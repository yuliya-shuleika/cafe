package com.yuliana.cafe.controller;

import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller class used to process all requests from users with url pattern '*.do'.
 *
 * @author Yulia Shuleiko
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Define tha action command and execute it.
     *
     * @param request the {@code HttpServletRequest} object
     * @param response the {@code HttpServletResponse} object
     * @throws ServletException if there is an error of the servlet
     * @throws IOException if occurred an error with I/O operations
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        String page;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request, response);
        page = command.execute(request, response);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            response.sendError(400);
        }
    }
}
