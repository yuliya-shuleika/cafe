package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.CafeService;
import com.yuliana.cafe.service.impl.CafeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ToPaymentCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        CafeService cafeService = CafeServiceImpl.getInstance();
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = cafeService.findAllCafeAddresses();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.CAFE_ADDRESSES, addresses);
        String page = PagePath.PAYMENT_PAGE;
        return page;
    }
}
