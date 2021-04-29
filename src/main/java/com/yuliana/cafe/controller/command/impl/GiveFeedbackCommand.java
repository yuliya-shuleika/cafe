package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Action command that provides giving a feedback from users.
 *
 * @author Yulia Shuleiko
 */
public class GiveFeedbackCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final int REVIEW_FORM_SIZE = 2;
    private static final String ERROR_MESSAGE = "error";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.REVIEWS_PAGE;
        ReviewService service = ReviewServiceImpl.getInstance();
        Map<String, String> reviewFields = new HashMap<>();
        fillReviewMap(reviewFields, request);
        String rating = request.getParameter(RequestParameter.REVIEW_RATING);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(AttributeName.USER);
        try {
            service.addReview(user.getUserId(), reviewFields, rating);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if (reviewFields.size() < REVIEW_FORM_SIZE) {
            request.setAttribute(AttributeName.EDIT_ERROR_MESSAGE, ERROR_MESSAGE);
            request.setAttribute(AttributeName.REVIEW_FIELDS, reviewFields);
        }
        try {
            Map<Review, User> reviewsWithAuthors = service.findApprovedReviewsWithAuthors();
            request.setAttribute(AttributeName.REVIEWS_MAP, reviewsWithAuthors);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }

    /**
     * Fill the map of string where key is field's name and values is a user's input.
     *
     * @param reviewFields map of the string.
     *                     The key represents field of the form and the value is the user's input
     * @param request the {@code HttpServletRequest} object
     */
    private void fillReviewMap(Map<String, String> reviewFields, HttpServletRequest request){
        String header = request.getParameter(RequestParameter.REVIEW_HEADER);
        reviewFields.put(RequestParameter.REVIEW_HEADER, header);
        String text = request.getParameter(RequestParameter.REVIEW_TEXT);
        reviewFields.put(RequestParameter.REVIEW_TEXT, text);
    }
}
