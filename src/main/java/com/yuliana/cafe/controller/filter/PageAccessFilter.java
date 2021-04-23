package com.yuliana.cafe.controller.filter;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "PageAccessFilter", urlPatterns = {"*.jsp"})
public class PageAccessFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "no_page_access";
    private static final String START_URI = "/index.jsp";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getServletPath();
        HttpSession session = req.getSession();
        boolean isUserPage = PagePath.userPages.stream().anyMatch(requestURI::contains);
        boolean isAdminPage = PagePath.adminPages.stream().anyMatch(requestURI::contains);
        boolean isGuestPage = PagePath.guestPages.stream().anyMatch(requestURI::contains);
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            UserRole role = user.getRole();
            if (role.equals(UserRole.USER) && (isUserPage || isGuestPage)) {
                chain.doFilter(request, response);
            } else if (role.equals(UserRole.ADMIN) && isAdminPage) {
                chain.doFilter(request, response);
            } else {
                resp.sendError(404);
            }
        } else {
            if(isGuestPage || requestURI.equals(START_URI)){
                chain.doFilter(request, response);
                return;
            }
            if(isAdminPage || isUserPage){
                request.setAttribute(AttributeName.LOGIN_ERROR_MESSAGE, ERROR_MESSAGE);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagePath.HOME_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                logger.log(Level.WARN, "Page doesn't exist.");
                resp.sendError(404);
            }
        }
    }
}
