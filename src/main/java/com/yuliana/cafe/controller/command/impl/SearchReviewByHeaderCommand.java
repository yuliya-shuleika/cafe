package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Action command that provides searching the review by the part of it's header.
 *
 * @author Yulia Shuleiko
 */
public class SearchReviewByHeaderCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = PagePath.REVIEWS_LIST_PAGE;
        String header = request.getParameter(RequestParameter.REVIEW_HEADER);
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            List<Review> reviews = reviewService.findReviewByHeader(header);
            request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
