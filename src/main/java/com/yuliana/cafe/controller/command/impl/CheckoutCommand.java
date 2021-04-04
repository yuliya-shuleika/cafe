package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
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
    //private static final String
    private static final String CITY_PARAM = "city";
    private static final String GETTING_TYPE_PARAM = "getting_type";
    private static final String ADDRESS_ID_PARAM = "getting_type";
    private static final String STREET_PARAM = "street";
    private static final String HOUSE_PARAM = "house";
    private static final String ENTRANCE_PARAM = "entrance";
    private static final String FLOOR_PARAM = "floor";
    private static final String FLAT_PARAM = "flat";
    private static final String PROMO_CODE_PARAM = "promo_code";
    private static final String DELIVERY = "delivery";
    private static final String PICKUP = "pickup";
    private static final String ORDER_COMMENT_PARAM = "order_comment";
    private static final String PAYMENT_TYPE_PARAM = "payment_type";
    private static final int ADDRESS_FORM_SIZE = 6;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String gettingType = request.getParameter(GETTING_TYPE_PARAM);
        GettingType getting = GettingType.valueOf(gettingType.toUpperCase());
        int addressId = 0;
        switch (gettingType){
            case DELIVERY:
                Map<String, String> addressFields = new HashMap<>();
                addressFields.put(CITY_PARAM, request.getParameter(CITY_PARAM));
                addressFields.put(STREET_PARAM, request.getParameter(STREET_PARAM));
                addressFields.put(HOUSE_PARAM, request.getParameter(HOUSE_PARAM));
                addressFields.put(ENTRANCE_PARAM, request.getParameter(ENTRANCE_PARAM));
                addressFields.put(FLOOR_PARAM, request.getParameter(FLOOR_PARAM));
                addressFields.put(FLAT_PARAM, request.getParameter(FLAT_PARAM));
                AddressService service = AddressServiceImpl.getInstance();
                try {
                    addressId = service.addAddress(addressFields);
                } catch (ServiceException e) {
                    logger.log(Level.ERROR, e);
                }
                request.setAttribute(AttributeName.ADDRESS_FIELDS, addressFields);
                if(addressFields.size() < ADDRESS_FORM_SIZE){
                    request.setAttribute(AttributeName.CHECKOUT_ERROR_MESSAGE, "Error filling address form.");
                }
                break;
            case PICKUP:
                String address = request.getParameter(ADDRESS_ID_PARAM);
                addressId = Integer.parseInt(address);
                break;
            default:
                logger.log(Level.ERROR, "No such getting type.");
        }
        Map<String, String[]> parameters = request.getParameterMap();
        int discount = 0;
        if (parameters.containsKey(PROMO_CODE_PARAM)) {
            String promoCodeName = request.getParameter(PROMO_CODE_PARAM);
            PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
            Optional<PromoCode> promoCode = null;
            try {
                promoCode = promoCodeService.findPromoCodeByName(promoCodeName);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
            if (promoCode.isPresent()) {
                discount = promoCode.get().getDiscountPercents();
                request.setAttribute(AttributeName.PROMO_CODE, promoCodeName);
            }
        }
        OrderService orderService = OrderServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        Map<Dish, Integer> cartItems = (Map<Dish, Integer>) session.getAttribute(AttributeName.CART_ITEMS);
        String comment = request.getParameter(ORDER_COMMENT_PARAM);
        String paymentType = request.getParameter(PAYMENT_TYPE_PARAM);
        PaymentType payment = PaymentType.valueOf(paymentType.toUpperCase());
        try {
            orderService.addOrder(user.getUserId(), addressId, discount, cartItems, getting, payment, comment);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        session.setAttribute(AttributeName.CART_ITEMS, new HashMap<Dish, Integer>());
        return PagePath.ORDER_CONFIRM_PAGE;
    }
}
