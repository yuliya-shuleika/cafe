package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
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
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToReviewsListCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            List<Review> reviews = reviewService.findAllReviews();
            request.setAttribute(AttributeName.REVIEWS_LIST, reviews);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.REVIEWS_LIST_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
