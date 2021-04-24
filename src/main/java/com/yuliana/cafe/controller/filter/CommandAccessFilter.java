package com.yuliana.cafe.controller.filter;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.CommandType;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Optional;

@WebFilter(filterName = "CommandAccessFilter")
public class CommandAccessFilter implements Filter {

    private EnumSet<CommandType> guestCommands;
    private EnumSet<CommandType> userCommands;
    private EnumSet<CommandType> adminCommands;
    private EnumSet<CommandType> allCommands;

    public void init(FilterConfig config) throws ServletException {
        guestCommands = EnumSet.of(
                CommandType.CHANGE_LOCALE,
                CommandType.TO_HOME,
                CommandType.TO_MENU,
                CommandType.TO_REVIEWS,
                CommandType.ADD_TO_CART,
                CommandType.DELETE_FROM_CART,
                CommandType.CLEAN_CART,
                CommandType.SEARCH_DISH_BY_NAME,
                CommandType.SORT_BY_PRICE,
                CommandType.SHOW_DISCOUNTS,
                CommandType.CHOOSE_CATEGORY,
                CommandType.SHOW_NEW_DISHES,
                CommandType.LOGIN,
                CommandType.REGISTER);
        userCommands = EnumSet.of(CommandType.CHANGE_LOCALE,
                CommandType.LOGOUT,
                CommandType.TO_MENU,
                CommandType.TO_HOME,
                CommandType.TO_REVIEWS,
                CommandType.TO_ACCOUNT,
                CommandType.CHECKOUT,
                CommandType.ADD_TO_CART,
                CommandType.DELETE_FROM_CART,
                CommandType.ADD_DISH_TO_FAVORITES,
                CommandType.CLEAN_CART,
                CommandType.SHOW_NEW_DISHES,
                CommandType.SHOW_DISCOUNTS,
                CommandType.CHOOSE_CATEGORY,
                CommandType.EDIT_ACCOUNT,
                CommandType.GIVE_FEEDBACK,
                CommandType.REPEAT_ORDER,
                CommandType.EDIT_USER_ADDRESS,
                CommandType.SEARCH_DISH_BY_NAME,
                CommandType.SHOW_USER_ADDRESS_EDIT,
                CommandType.TO_PAYMENT);
        adminCommands = EnumSet.of(CommandType.CHANGE_LOCALE,
                CommandType.LOGOUT,
                CommandType.TO_REVIEWS_LIST,
                CommandType.TO_DISHES_LIST,
                CommandType.TO_PROMO_CODES_LIST,
                CommandType.TO_USERS_LIST,
                CommandType.SEARCH_DISH_BY_NAME,
                CommandType.ADD_DISH_TO_MENU,
                CommandType.EDIT_DISH,
                CommandType.DELETE_DISH_FROM_MENU,
                CommandType.BLOCK_USER,
                CommandType.UNBLOCK_USER,
                CommandType.CHANGE_USER_ROLE,
                CommandType.SHOW_DISH_EDIT,
                CommandType.SEARCH_REVIEW_BY_HEADER,
                CommandType.SEARCH_PROMO_CODE_BY_NAME_PART,
                CommandType.SEARCH_USER_BY_EMAIL,
                CommandType.SORT_PROMO_CODES_BY_NAME,
                CommandType.SORT_DISHES_BY_NAME,
                CommandType.SORT_REVIEWS_BY_HEADER,
                CommandType.SORT_USERS_BY_EMAIL,
                CommandType.UPDATE_REVIEW_STATUS,
                CommandType.DELETE_REVIEW,
                CommandType.SHOW_NEW_REVIEWS,
                CommandType.SHOW_PROMO_CODE_EDIT,
                CommandType.ADD_PROMO_CODE,
                CommandType.EDIT_PROMO_CODE,
                CommandType.DELETE_PROMO_CODE);
        allCommands = EnumSet.allOf(CommandType.class);
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String command = request.getParameter(RequestParameter.COMMAND);
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        if(!allCommands.contains(commandType)){
            resp.sendError(400);
        }
        Object userAttribute = session.getAttribute(AttributeName.USER);
        Optional<Object> userOptional = Optional.ofNullable(userAttribute);
        if (userOptional.isPresent()) {
            User user = (User) userOptional.get();
            UserRole role = user.getRole();
            if(role.equals(UserRole.USER) && userCommands.contains(commandType)){
                chain.doFilter(request, response);
            } else if(role.equals(UserRole.ADMIN) && adminCommands.contains(commandType)){
                chain.doFilter(request, response);
            } else {
                resp.sendError(403);
            }
        } else {
            if(guestCommands.contains(commandType)){
                chain.doFilter(request, response);
            } else {
                resp.sendError(403);
            }
        }
    }
}
