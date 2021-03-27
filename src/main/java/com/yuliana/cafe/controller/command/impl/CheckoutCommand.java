package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.entity.User;
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

    private final static Logger logger = LogManager.getLogger();
    private static final String CITY_PARAM = "city";
    private static final String STREET_PARAM = "street";
    private static final String HOUSE_PARAM = "house";
    private static final String ENTRANCE_PARAM = "entrance";
    private static final String FLOOR_PARAM = "floor";
    private static final String FLAT_PARAM = "flat";
    private static final String PROMO_CODE_PARAM = "promo_code";
    private static final String TOTAL_PARAM = "total";

    private static final int ADDRESS_FORM_SIZE = 6;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> addressFields = new HashMap<>();
        addressFields.put(CITY_PARAM, request.getParameter(CITY_PARAM));
        addressFields.put(STREET_PARAM, request.getParameter(STREET_PARAM));
        addressFields.put(HOUSE_PARAM, request.getParameter(HOUSE_PARAM));
        addressFields.put(ENTRANCE_PARAM, request.getParameter(ENTRANCE_PARAM));
        addressFields.put(FLOOR_PARAM, request.getParameter(FLOOR_PARAM));
        addressFields.put(FLAT_PARAM, request.getParameter(FLAT_PARAM));
        AddressService service = AddressServiceImpl.getInstance();
        int addressId;
        try {
            addressId = service.addAddress(addressFields);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return PagePath.ERROR_PAGE;
        }
        request.setAttribute(AttributeName.ADDRESS_FIELDS, addressFields);
        if(addressFields.size() < ADDRESS_FORM_SIZE){
             request.setAttribute(AttributeName.CHECKOUT_ERROR_MESSAGE, "Error filling address form.");
        }
        Map<String, String[]> parameters = request.getParameterMap();
        int discount = 0;
        if (parameters.containsKey(PROMO_CODE_PARAM)) {
            String promoCodeName = request.getParameter(PROMO_CODE_PARAM);
            PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
            Optional<PromoCode> promoCode;
            try {
                promoCode = promoCodeService.findPromoCodeByName(promoCodeName);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
                return PagePath.ERROR_PAGE;
            }
            if (promoCode.isPresent()) {
                discount = promoCode.get().getDiscountPercents();
                request.setAttribute(AttributeName.PROMO_CODE, promoCodeName);
            }
        }
        String total = request.getParameter(TOTAL_PARAM);
        double totalPrice = Double.parseDouble(total);
        OrderService orderService = OrderServiceImpl.getInstance();
        int orderId;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        try {
            orderId = orderService.addOrder(user, addressId, totalPrice, discount);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            return PagePath.ERROR_PAGE;
        }
        //add ordered dishes
        //Map<Dish, Integer> orderedDishes =
        //HttpSession session = request.getSession();
        //User user = (User) session.getAttribute(AttributeName.USER);
        return PagePath.MENU_PAGE;
    }
}
