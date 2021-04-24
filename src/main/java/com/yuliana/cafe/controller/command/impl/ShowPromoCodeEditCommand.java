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
import java.util.List;
import java.util.Optional;

public class ShowPromoCodeEditCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        String promoCodeIdParam = request.getParameter(RequestParameter.PROMO_CODE_ID);
        int promoCodeId = Integer.parseInt(promoCodeIdParam);
        Optional<PromoCode> promoCodeOptional = Optional.empty();
        try {
            promoCodeOptional = promoCodeService.findPromoCodeById(promoCodeId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (promoCodeOptional.isPresent()) {
            PromoCode promoCode = promoCodeOptional.get();
            request.setAttribute(AttributeName.SELECTED_PROMO_CODE, promoCode);
        }
        try {
            List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
            request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        return page;
    }
}
