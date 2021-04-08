package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.AddressService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.AddressServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class EditAccountCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String NAME_PARAM = "name";
    private static final String EMAIL_PARAM = "email";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> userForm  = new HashMap<>();
        userForm.put(NAME_PARAM, request.getParameter(NAME_PARAM));
        userForm.put(EMAIL_PARAM, request.getParameter(EMAIL_PARAM));
        UserService userService = UserServiceImpl.getInstance();
        try {
            userService.editUser(userForm);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.ACCOUNT_PAGE;
        return page;
    }
}
