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
import java.io.IOException;

/**
 * Action command that provides update of the review status.
 *
 * @author Yulia Shuleiko
 */
public class UpdateReviewStatusCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.REVIEWS_LIST_PAGE;
        String status = request.getParameter(RequestParameter.REVIEW_STATUS);
        String reviewIdParam = request.getParameter(RequestParameter.REVIEW_ID);
        int reviewId = Integer.parseInt(reviewIdParam);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            reviewService.updateStatus(status, reviewId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        return page;
    }
}
