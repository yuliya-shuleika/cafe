package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.UserService;
import com.yuliana.cafe.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowUserAddressEditCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        try {
            Optional<Address> addressOptional = userService.findUserAddress(userId);
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                request.setAttribute(AttributeName.USER_ADDRESS, address);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.EDIT_ADDRESS, true);
        String page = PagePath.ACCOUNT_PAGE;
        return page;
    }
}
