package com.yuliana.cafe.controller.filter;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * Filter prevents access to some pages if user doesn't have rights for it.
 *
 * @author Yulia Shuleiko
 */
@WebFilter(filterName = "PageAccessFilter")
public class PageAccessFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "no_page_access";
    private static final String START_URI = "/index.jsp";
    private Set<String> guestPages;
    private Set<String> userPages;
    private Set<String> adminPages;

    public void init(FilterConfig config) throws ServletException {
        guestPages = Set.of(PagePath.HOME_PAGE,
                PagePath.MENU_PAGE,
                PagePath.REVIEWS_PAGE,
                PagePath.PAYMENT_PAGE,
                PagePath.ORDER_CONFIRM_PAGE);
        userPages = Set.of(PagePath.HOME_PAGE,
                PagePath.MENU_PAGE,
                PagePath.REVIEWS_PAGE,
                PagePath.PAYMENT_PAGE,
                PagePath.ORDER_CONFIRM_PAGE,
                PagePath.ACCOUNT_PAGE);
        adminPages = Set.of(PagePath.USERS_LIST_PAGE,
                PagePath.DISHES_LIST_PAGE,
                PagePath.PROMO_CODES_LIST_PAGE,
                PagePath.REVIEWS_LIST_PAGE);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getServletPath();
        HttpSession session = req.getSession();
        boolean isUserPage = userPages.stream().anyMatch(requestURI::contains);
        boolean isAdminPage = adminPages.stream().anyMatch(requestURI::contains);
        boolean isGuestPage = guestPages.stream().anyMatch(requestURI::contains);
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
