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
import java.util.ArrayList;
import java.util.List;

/**
 * Action command that provides searching the promo code by the part of it's name.
 *
 * @author Yulia Shuleiko
 */
public class SearchPromoCodeByNamePartCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        String promoCodeNamePart = request.getParameter(RequestParameter.PROMO_CODE_NAME_PART_PARAM);
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        List<PromoCode> promoCodes = new ArrayList<>();
        try {
            promoCodes = promoCodeService.findPromoCodesByNamePart(promoCodeNamePart);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        return page;
    }
}
