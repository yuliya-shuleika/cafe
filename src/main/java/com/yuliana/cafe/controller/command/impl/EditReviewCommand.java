package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditReviewCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String REVIEW_ID_PARAM = "review_id";
    private static final String REVIEW_HEADER_PARAM = "review_header";
    private static final String REVIEW_TEXT_PARAM = "review_text";
    private static final String REVIEW_RATING_PARAM = "review_rating";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String reviewIdParam = request.getParameter(REVIEW_ID_PARAM);
        int reviewId = Integer.parseInt(reviewIdParam);
        String header = request.getParameter(REVIEW_HEADER_PARAM);
        String text = request.getParameter(REVIEW_TEXT_PARAM);
        String ratingParam = request.getParameter(REVIEW_RATING_PARAM);
        int rating = Integer.parseInt(REVIEW_RATING_PARAM);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            reviewService.editReview(reviewId, header, text, rating);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.REVIEWS_LIST_PAGE;
        return page;
    }
}
