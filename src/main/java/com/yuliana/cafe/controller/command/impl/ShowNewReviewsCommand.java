package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
import com.yuliana.cafe.controller.command.ActionCommand;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.ReviewStatus;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowNewReviewsCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String page = PagePath.REVIEWS_LIST_PAGE;
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            List<Review> reviews = reviewService.findReviewsByStatus(ReviewStatus.NEW);
            request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            response.sendError(500);
        }
        return page;
    }
}
