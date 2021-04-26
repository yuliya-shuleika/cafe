package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.CafeService;
import com.yuliana.cafe.model.service.impl.CafeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Action command that provides transition to the payment page.
 *
 * @author Yulia Shuleiko
 */
public class ToPaymentCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.PAYMENT_PAGE;
        CafeService cafeService = CafeServiceImpl.getInstance();
        try {
            List<Address> addresses = cafeService.findAllCafeAddresses();
            request.setAttribute(AttributeName.CAFE_ADDRESSES, addresses);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
