package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.PromoCode;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.PromoCodeService;
import com.yuliana.cafe.model.service.impl.PromoCodeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action command that provides adding the promo code.
 *
 * @author Yulia Shuleiko
 */
public class AddPromoCodeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "add_error";
    private static final int PROMO_CODE_FORM_SIZE = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        Map<String, String> promoCodeFields = new HashMap<>();
        String name = request.getParameter(RequestParameter.PROMO_CODE_NAME);
        promoCodeFields.put(RequestParameter.PROMO_CODE_NAME, name);
        String discountPercents = request.getParameter(RequestParameter.PROMO_CODE_DISCOUNT_PERCENTS);
        promoCodeFields.put(RequestParameter.PROMO_CODE_DISCOUNT_PERCENTS, discountPercents);
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        try {
            promoCodeService.addPromoCode(promoCodeFields);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (promoCodeFields.size() < PROMO_CODE_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.PROMO_CODE_FIELDS, promoCodeFields);
            logger.log(Level.DEBUG, "Error while filling promo code form.");
        }
        try {
            List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
            request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
