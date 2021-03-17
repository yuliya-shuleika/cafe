package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.PagePath;
import com.yuliana.cafe.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String city = request.getParameter(CITY_PARAM);
        String street = request.getParameter(STREET_PARAM);
        short house = Short.parseShort(request.getParameter(HOUSE_PARAM));
        short entrance = Short.parseShort(request.getParameter(ENTRANCE_PARAM));
        short floor = Short.parseShort(request.getParameter(FLOOR_PARAM));
        short flat = Short.parseShort(request.getParameter(FLAT_PARAM));
        String promoCode = request.getParameter(PROMO_CODE_PARAM);
        double total = Double.parseDouble(request.getParameter(TOTAL_PARAM));

        //Map<Dish, Integer> orderedDishes =
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        return PagePath.HOME_PAGE;
    }
}
