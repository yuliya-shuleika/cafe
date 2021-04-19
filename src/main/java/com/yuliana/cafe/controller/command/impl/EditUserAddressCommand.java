package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Address;
import com.yuliana.cafe.entity.Order;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.AddressService;
import com.yuliana.cafe.service.OrderService;
import com.yuliana.cafe.service.UserService;
import com.yuliana.cafe.service.impl.AddressServiceImpl;
import com.yuliana.cafe.service.impl.OrderServiceImpl;
import com.yuliana.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EditUserAddressCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "edit_address_error";
    private static final int ADDRESS_FORM_SIZE = 6;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> addressFields = new HashMap<>();
        String city = request.getParameter(RequestParameter.CITY);
        addressFields.put(RequestParameter.CITY, city);
        String street = request.getParameter(RequestParameter.STREET);
        addressFields.put(RequestParameter.STREET, street);
        String house = request.getParameter(RequestParameter.HOUSE);
        addressFields.put(RequestParameter.HOUSE, house);
        String entrance = request.getParameter(RequestParameter.ENTRANCE);
        addressFields.put(RequestParameter.ENTRANCE, entrance);
        String floor = request.getParameter(RequestParameter.FLOOR);
        addressFields.put(RequestParameter.FLOOR, floor);
        String flat = request.getParameter(RequestParameter.FLAT);
        addressFields.put(RequestParameter.FLAT, flat);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        int userId = user.getUserId();
        try {
            Optional<Address> addressOptional = userService.findUserAddress(userId);
            AddressService addressService = AddressServiceImpl.getInstance();
            int addressId;
            if (addressOptional.isPresent()) {
                Address address = addressOptional.get();
                addressId = address.getAddressId();
                addressService.updateAddress(addressFields, addressId);
                request.setAttribute(AttributeName.USER_ADDRESS, address);
            } else {
                addressId = addressService.addAddress(addressFields);
                userService.addUserAddress(addressId, userId);
                addressOptional = addressService.findAddressById(addressId);
                if (addressOptional.isPresent()) {
                    Address address = addressOptional.get();
                    request.setAttribute(AttributeName.USER_ADDRESS, address);
                }
            }
            if (addressFields.size() < ADDRESS_FORM_SIZE) {
                request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
                request.setAttribute(AttributeName.ADDRESS_FIELDS, addressFields);
                request.setAttribute(AttributeName.ADDRESS_ID, addressId);
            }
            OrderService orderService = OrderServiceImpl.getInstance();
            try {
                List<Order> orders = orderService.findOrdersByUserId(userId);
                request.setAttribute(AttributeName.USER_ORDERS, orders);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.ACCOUNT_PAGE;
        return page;
    }
}
