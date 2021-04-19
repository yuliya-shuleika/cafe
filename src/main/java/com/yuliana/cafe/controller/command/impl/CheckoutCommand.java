package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.*;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.AddressService;
import com.yuliana.cafe.service.OrderService;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.impl.AddressServiceImpl;
import com.yuliana.cafe.service.impl.OrderServiceImpl;
import com.yuliana.cafe.service.impl.PromoCodeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CheckoutCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ADDRESS_ERROR_MESSAGE = "address_error";
    private static final String PROMO_CODE_ERROR_MESSAGE = "address_error";
    private static final String COMMENT_ERROR_MESSAGE = "address_error";
    private static final String DELIVERY = "delivery";
    private static final String PICKUP = "pickup";
    private static final int ADDRESS_FORM_SIZE = 6;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String gettingType = request.getParameter(RequestParameter.ORDER_GETTING_TYPE);
        GettingType getting = GettingType.valueOf(gettingType.toUpperCase());
        int addressId = 0;
        switch (gettingType) {
            case DELIVERY:
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
                AddressService service = AddressServiceImpl.getInstance();
                try {
                    addressId = service.addAddress(addressFields);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                request.setAttribute(AttributeName.ADDRESS_FIELDS, addressFields);
                if (addressFields.size() < ADDRESS_FORM_SIZE) {
                    request.setAttribute(AttributeName.CHECKOUT_ERROR_MESSAGE, ADDRESS_ERROR_MESSAGE);
                    request.setAttribute(AttributeName.ADDRESS_FIELDS, addressFields);
                }
                break;
            case PICKUP:
                String address = request.getParameter(RequestParameter.ADDRESS_ID);
                addressId = Integer.parseInt(address);
                break;
            default:
                logger.log(Level.DEBUG, "No such getting type.");
        }
        if (addressId > 0) {
            Map<String, String[]> parameters = request.getParameterMap();
            int discount = 0;
            if (parameters.containsKey(RequestParameter.PROMO_CODE_NAME)) {
                String promoCodeName = request.getParameter(RequestParameter.PROMO_CODE_NAME);
                PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
                Optional<PromoCode> promoCode = Optional.empty();
                try {
                    promoCode = promoCodeService.findPromoCodeByName(promoCodeName);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                if (promoCode.isPresent()) {
                    discount = promoCode.get().getDiscountPercents();
                    request.setAttribute(AttributeName.PROMO_CODE, promoCodeName);
                } else {
                    request.setAttribute(AttributeName.CHECKOUT_ERROR_MESSAGE, PROMO_CODE_ERROR_MESSAGE);
                }
            }
            OrderService orderService = OrderServiceImpl.getInstance();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(AttributeName.USER);
            Map<Dish, Integer> cartItems = (Map<Dish, Integer>) session.getAttribute(AttributeName.CART_ITEMS);
            String comment = request.getParameter(RequestParameter.ORDER_COMMENT);
            String paymentType = request.getParameter(RequestParameter.ORDER_PAYMENT_TYPE);
            PaymentType payment = PaymentType.valueOf(paymentType.toUpperCase());
            try {
                orderService.addOrder(user.getUserId(), addressId, discount, cartItems, getting, payment, comment);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
            session.setAttribute(AttributeName.CART_ITEMS, new HashMap<Dish, Integer>());
            page = PagePath.ORDER_CONFIRM_PAGE;
        } else {
            page = PagePath.PAYMENT_PAGE;
        }
        return page;
    }
}
