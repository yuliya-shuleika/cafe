package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.controller.PagePath;
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
import java.util.HashMap;
import java.util.Map;

public class GiveFeedbackCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String REVIEW_HEADER_PARAM = "review_header";
    private static final String REVIEW_RATING_PARAM = "review_rating";
    private static final String REVIEW_TEXT_PARAM = "review_text";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        ReviewService service = ReviewServiceImpl.getInstance();
        Map<String, String> reviewFields = new HashMap<>();
        reviewFields.put(REVIEW_HEADER_PARAM, request.getParameter(REVIEW_HEADER_PARAM));
        reviewFields.put(REVIEW_TEXT_PARAM, request.getParameter(REVIEW_TEXT_PARAM));
        String rating = request.getParameter(REVIEW_RATING_PARAM);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(AttributeName.USER);
        try {
            service.addReview(user.getUserId(), reviewFields, rating);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        page = PagePath.REVIEWS_PAGE;
        return page;
    }
}
