package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ToPromoCodesListCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        List<PromoCode> promoCodes = new ArrayList<>();
        try {
            promoCodes = promoCodeService.findAllPromoCodes();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }

}
