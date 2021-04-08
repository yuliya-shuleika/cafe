package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.GettingType;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.OrderService;
import com.yuliana.cafe.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RepeatOrderCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private static final String CITY_PARAM = "city";
    private static final String STREET_PARAM = "street";
    private static final String HOUSE_PARAM = "house";
    private static final String ENTRANCE_PARAM = "entrance";
    private static final String FLOOR_PARAM = "floor";
    private static final String FLAT_PARAM = "flat";
    private static final String ORDER_ID_PARAM = "order_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderIdParam = request.getParameter(ORDER_ID_PARAM);
        int orderId = Integer.parseInt(orderIdParam);
        OrderService orderService = OrderServiceImpl.getInstance();
        Optional<Order> orderOptional = Optional.empty();
        try {
            orderOptional = orderService.findOrderById(orderId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            if(order.getGettingType().equals(GettingType.DELIVERY)){
                //Optional<Address> addressOptional = orderService.
            }
        }
        String page = PagePath.PAYMENT_PAGE;
        return page;
    }
}
