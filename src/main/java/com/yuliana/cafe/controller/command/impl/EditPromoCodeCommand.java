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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action command that provides editing the promo code.
 *
 * @author Yulia Shuleiko
 */
public class EditPromoCodeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "edit_error";
    private static final int PROMO_CODE_FORM_SIZE = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.PROMO_CODES_LIST_PAGE;

        Map<String, String> promoCodeFields = new HashMap<>();
        fillPromoCodeMap(promoCodeFields, request);
        String promoCodeIdParam = request.getParameter(RequestParameter.PROMO_CODE_ID);
        int promoCodeId = Integer.parseInt(promoCodeIdParam);
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        try {
            promoCodeService.editPromoCode(promoCodeFields, promoCodeId);
            List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
            request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        if (promoCodeFields.size() < PROMO_CODE_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.PROMO_CODE_FIELDS, promoCodeFields);
            request.setAttribute(AttributeName.PROMO_CODE_ID, promoCodeId);
        }
        return page;
    }

    /**
     * Fill the map of string where key is field's name and values is a user's input.
     *
     * @param promoCodeFields map of the string.
     *                     The key represents field of the form and the value is the user's input
     * @param request the {@code HttpServletRequest} object
     */
    private void fillPromoCodeMap(Map<String, String> promoCodeFields, HttpServletRequest request){
        String name = request.getParameter(RequestParameter.PROMO_CODE_NAME);
        promoCodeFields.put(RequestParameter.PROMO_CODE_NAME, name);
        String discountPercents = request.getParameter(RequestParameter.PROMO_CODE_DISCOUNT_PERCENTS);
        promoCodeFields.put(RequestParameter.PROMO_CODE_DISCOUNT_PERCENTS, discountPercents);
    }
}
