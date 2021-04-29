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
import java.io.IOException;
import java.util.List;

/**
 * Action command that provides transition to the promo codes list admin page.
 *
 * @author Yulia Shuleiko
 */
public class ToPromoCodesListCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PromoCodeService promoCodeService = PromoCodeServiceImpl.getInstance();
        try {
            List<PromoCode> promoCodes = promoCodeService.findAllPromoCodes();
            request.setAttribute(AttributeName.PROMO_CODES_LIST, promoCodes);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        String page = PagePath.PROMO_CODES_LIST_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }

}
