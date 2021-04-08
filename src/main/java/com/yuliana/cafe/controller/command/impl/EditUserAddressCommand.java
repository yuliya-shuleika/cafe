package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EditUserAddressCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String CITY_PARAM = "city";
    private static final String STREET_PARAM = "street";
    private static final String HOUSE_PARAM = "house";
    private static final String ENTRANCE_PARAM = "entrance";
    private static final String FLOOR_PARAM = "floor";
    private static final String FLAT_PARAM = "flat";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> addressFields = new HashMap<>();
        addressFields.put(CITY_PARAM, request.getParameter(CITY_PARAM));
        addressFields.put(STREET_PARAM, request.getParameter(STREET_PARAM));
        addressFields.put(HOUSE_PARAM, request.getParameter(HOUSE_PARAM));
        addressFields.put(ENTRANCE_PARAM, request.getParameter(ENTRANCE_PARAM));
        addressFields.put(FLOOR_PARAM, request.getParameter(FLOOR_PARAM));
        addressFields.put(FLAT_PARAM, request.getParameter(FLAT_PARAM));
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        try {
            userService.editUserAddress(addressFields, userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.ACCOUNT_PAGE;
        return null;
    }
}
