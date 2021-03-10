package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.command.PagePath;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GiveFeedbackCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String PARAM_REVIEW_HEADER = "review_header";
    private static final String PARAM_REVIEW_RATING = "review_rating";
    private static final String PARAM_REVIEW_TEXT = "review_text";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        ReviewService service = ReviewServiceImpl.getInstance();
        String header = request.getParameter(PARAM_REVIEW_HEADER);
        String rating = request.getParameter(PARAM_REVIEW_RATING);
        String text = request.getParameter(PARAM_REVIEW_TEXT);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeName.USER);
        try {
            service.addReview(user.getUserId(), header, text, rating);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        page = PagePath.MENU_PAGE;
        return page;
    }
}
