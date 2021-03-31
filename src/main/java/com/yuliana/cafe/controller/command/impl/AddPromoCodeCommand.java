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
        Map<String, String> addPromoCodeFields = new HashMap<>();
        addPromoCodeFields.put(PROMO_CODE_NAME_PARAM, request.getParameter(PROMO_CODE_NAME_PARAM));
        addPromoCodeFields.put(PROMO_CODE_DISCOUNT_PERCENTS_PARAM,
                request.getParameter(PROMO_CODE_DISCOUNT_PERCENTS_PARAM));
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        int promoCodeId = 0;
        try {
            promoCodeId = promoCodeService.addPromoCode(addPromoCodeFields);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        PromoCode promoCode;
        if(addPromoCodeFields.size() == PROMO_CODE_FORM_SIZE){
            try {
                Optional<PromoCode> promoCodeOptional = promoCodeService.findPromoCodeById(promoCodeId);
                if(promoCodeOptional.isPresent()) {
                    promoCode = promoCodeOptional.get();
                    request.setAttribute(AttributeName.PROMO_CODE, promoCode);
                }
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
