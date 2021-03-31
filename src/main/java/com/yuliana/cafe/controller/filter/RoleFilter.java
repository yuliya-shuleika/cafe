package com.yuliana.cafe.controller.filter;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.CommandType;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "RoleFilter", urlPatterns = { "/controller", "*.do" })
public class RoleFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND_PARAM = "command";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        UserRole role;
        User user = (User) session.getAttribute(AttributeName.USER);
        if(user != null) {
            role = user.getRole();
        } else {
            role = UserRole.GUEST;
        }
        String command = httpRequest.getParameter(COMMAND_PARAM);
        CommandType commandType;
        if(command != null){
            commandType = CommandType.valueOf(command.toUpperCase());
        } else {
            commandType = CommandType.TO_MENU;
        }
        switch (commandType){
            case CHECKOUT:
                if(!role.equals(UserRole.USER)) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.ERROR_PAGE);
                    logger.log(Level.DEBUG, "This command for user only");
                } else {
                    chain.doFilter(request, response);
                }
                break;
            case EDIT_DISH:
                if(!role.equals(UserRole.ADMIN)) {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.ERROR_PAGE);
                    logger.log(Level.DEBUG, "This command for admin only");
                } else {
                    chain.doFilter(request, response);
                }
                break;
            default:
                chain.doFilter(request, response);
        }
    }
}
