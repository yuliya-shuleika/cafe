package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action command that provides deleting the review.
 *
 * @author Yulia Shuleiko
 */
public class DeleteReviewCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.REVIEWS_LIST_PAGE;
        String reviewIdParam = request.getParameter(RequestParameter.REVIEW_ID);
        int reviewId = Integer.parseInt(reviewIdParam);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            reviewService.deleteReview(reviewId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}