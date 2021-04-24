package com.yuliana.cafe.controller.command.impl;

import com.yuliana.cafe.controller.AttributeName;
import com.yuliana.cafe.controller.PagePath;
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
import java.util.Map;

public class ToReviewsCommand implements ActionCommand {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ReviewService reviewService = ReviewServiceImpl.getInstance();
        try {
            Map<Review, User> reviewsWithAuthors = reviewService.findApprovedReviewsWithAuthors();
            request.setAttribute(AttributeName.REVIEWS_MAP, reviewsWithAuthors);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        String page = PagePath.REVIEWS_PAGE;
        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.CURRENT_PAGE, page);
        return page;
    }
}
