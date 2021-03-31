package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.PromoCodeService;
import com.yuliana.cafe.service.impl.PromoCodeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePromoCodeCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PROMO_CODE_ID_PARAM = "promo_code_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String promoCodeIdParam = request.getParameter(PROMO_CODE_ID_PARAM);
        int promoCodeId = Integer.parseInt(promoCodeIdParam);
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        try {
            promoCodeService.deletePromoCode(promoCodeId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        return page;
    }
}
