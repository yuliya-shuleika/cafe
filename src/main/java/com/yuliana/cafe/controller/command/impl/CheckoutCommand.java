package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.entity.PromoCode;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.model.entity.Dish;
import com.yuliana.cafe.model.entity.PaymentType;
import com.yuliana.cafe.model.entity.GettingType;
import com.yuliana.cafe.model.service.AddressService;
import com.yuliana.cafe.model.service.OrderService;
import com.yuliana.cafe.model.service.PromoCodeService;
import com.yuliana.cafe.model.service.impl.AddressServiceImpl;
import com.yuliana.cafe.model.service.impl.OrderServiceImpl;
import com.yuliana.cafe.model.service.impl.PromoCodeServiceImpl;
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
    private static final String PROMO_CODE_ERROR_MESSAGE = "promo_code_error";
    private static final String DELIVERY = "delivery";
    private static final String PICKUP = "pickup";
    private static final int ADDRESS_FORM_SIZE = 6;
    private String page;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        page = PagePath.ORDER_CONFIRM_PAGE;
        String gettingType = request.getParameter(RequestParameter.ORDER_GETTING_TYPE);
        GettingType getting = GettingType.valueOf(gettingType.toUpperCase());
        int addressId = 0;
        switch (gettingType) {
            case DELIVERY:
                Map<String, String> addressFields = new HashMap<>();
                fillAddressMap(addressFields, request);
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
            int discount = defineDiscountByPromoCode(request);
            String comment = request.getParameter(RequestParameter.ORDER_COMMENT);
            String paymentType = request.getParameter(RequestParameter.ORDER_PAYMENT_TYPE);
            PaymentType payment = PaymentType.valueOf(paymentType.toUpperCase());
            HttpSession session = request.getSession();
            Map<Dish, Integer> cartItems = (Map<Dish, Integer>) session.getAttribute(AttributeName.CART_ITEMS);
            OrderService orderService = OrderServiceImpl.getInstance();
            Object userAttribute = session.getAttribute(AttributeName.USER);
            Optional<Object> userOptional = Optional.ofNullable(userAttribute);
            if(userOptional.isPresent()) {
                User user = (User) userOptional.get();
                try {
                    orderService.addUserOrder(user.getUserId(), addressId, discount, cartItems, getting, payment, comment);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
            } else {
                String guestEmail = request.getParameter(RequestParameter.GUEST_EMAIL);
                try {
                    orderService.addGuestOrder(guestEmail, addressId, discount, cartItems, getting, payment, comment);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
            }
            session.setAttribute(AttributeName.CART_ITEMS, new HashMap<Dish, Integer>());
        } else {
            page = PagePath.PAYMENT_PAGE;
        }
        return page;
    }

    /**
     * Fill the map of string where key is field's name and values is a user's input.
     * @param addressFields map of the string.
     *                      The key represents field of the form and the value is the user's input
     * @param request the {@code HttpServletRequest} object
     */
    private void fillAddressMap(Map<String, String> addressFields, HttpServletRequest request){
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
    }

    /**
     * Define the discount by the promo code's name.
     *
     * @param request the {@code HttpServletRequest} object
     * @return discount percents
     */
    private int defineDiscountByPromoCode(HttpServletRequest request){
        String promoCodeName = request.getParameter(RequestParameter.PROMO_CODE_NAME);
        int discount = 0;
        if (!promoCodeName.equals("")) {
            PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
            try {
                Optional<PromoCode> promoCode = promoCodeService.findPromoCodeByName(promoCodeName);
                if (promoCode.isPresent()) {
                    discount = promoCode.get().getDiscountPercents();
                    request.setAttribute(AttributeName.PROMO_CODE, promoCodeName);
                } else {
                    request.setAttribute(AttributeName.CHECKOUT_ERROR_MESSAGE, PROMO_CODE_ERROR_MESSAGE);
                    page = PagePath.PAYMENT_PAGE;
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return discount;
    }
}
