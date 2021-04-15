package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.RequestParameter;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ShowReviewEditCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        String reviewIdParam = request.getParameter(RequestParameter.REVIEW_ID);
        int reviewId = Integer.parseInt(reviewIdParam);
        Optional<Review> reviewOptional = Optional.empty();
        try {
            reviewOptional = reviewService.findReviewById(reviewId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            request.setAttribute(AttributeName.SELECTED_REVIEW, review);
        }
        String page = PagePath.REVIEWS_LIST_PAGE;
        return page;
    }
}
