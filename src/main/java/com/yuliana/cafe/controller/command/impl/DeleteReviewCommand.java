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

public class DeleteReviewCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();
    private static final String REVIEW_ID_PARAM = "review_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String reviewIdParam = request.getParameter(REVIEW_ID_PARAM);
        int reviewId = Integer.parseInt(reviewIdParam);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            reviewService.deleteReview(reviewId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.REVIEWS_LIST_PAGE;
        return page;
    }
}