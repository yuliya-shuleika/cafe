package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.PromoCode;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.impl.PromoCodeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddPromoCodeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PROMO_CODE_NAME_PARAM = "promo_code_name";
    private static final String PROMO_CODE_DISCOUNT_PERCENTS_PARAM = "promo_code_discount_percents";
    private static final String ERROR_MESSAGE = "Please, fill all the fields right.";
    private static final int PROMO_CODE_FORM_SIZE = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> promoCodeFields = new HashMap<>();
        promoCodeFields.put(PROMO_CODE_NAME_PARAM, request.getParameter(PROMO_CODE_NAME_PARAM));
        promoCodeFields.put(PROMO_CODE_DISCOUNT_PERCENTS_PARAM,
                request.getParameter(PROMO_CODE_DISCOUNT_PERCENTS_PARAM));
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        try {
            promoCodeService.addPromoCode(promoCodeFields);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(promoCodeFields.size() == PROMO_CODE_FORM_SIZE){
            List<PromoCode> promoCodes;
            try {
                promoCodes = promoCodeService.findAllPromoCodes();
                request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
            } catch (ServiceException e) {
                logger.log(Level.ERROR, e);
            }
        } else {
            request.setAttribute(AttributeName.ADD_ITEM_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        return page;
    }
}
