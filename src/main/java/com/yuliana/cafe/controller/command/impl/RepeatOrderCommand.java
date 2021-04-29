package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Address;
import com.yuliana.cafe.model.entity.GettingType;
import com.yuliana.cafe.model.entity.Order;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.OrderService;
import com.yuliana.cafe.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Action command that provides repeating order from user's orders list.
 *
 * @author Yulia Shuleiko
 */
public class RepeatOrderCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.PAYMENT_PAGE;
        String orderIdParam = request.getParameter(RequestParameter.ORDER_ID);
        int orderId = Integer.parseInt(orderIdParam);
        OrderService orderService = OrderServiceImpl.getInstance();
        Optional<Order> orderOptional = Optional.empty();
        try {
            orderOptional = orderService.findOrderById(orderId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            request.setAttribute(AttributeName.REPEATED_ORDER, order);
            if (order.getGettingType().equals(GettingType.DELIVERY)) {
                try {
                    Optional<Address> addressOptional = orderService.findAddressByOrderId(orderId);
                    if (addressOptional.isPresent()) {
                        Address address = addressOptional.get();
                        request.setAttribute(AttributeName.ORDER_ADDRESS, address);
                    }
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                    response.sendError(500);
                }
            }
        }
        return page;
    }
}
