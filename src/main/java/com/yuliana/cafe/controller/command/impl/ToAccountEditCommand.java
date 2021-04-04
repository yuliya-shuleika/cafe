package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Address;
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
import java.util.Optional;

public class ToAccountEditCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        UserService userService = UserServiceImpl.getInstance();
        Optional<Address> addressOptional = Optional.empty();
        try {
            addressOptional = userService.findUserAddress(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();
            request.setAttribute(AttributeName.USER_ADDRESS, address);
        }
        String page = PagePath.ACCOUNT_EDIT_PAGE;
        return page;
    }
}
