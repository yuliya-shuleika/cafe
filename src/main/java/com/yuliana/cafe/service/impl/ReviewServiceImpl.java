package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.ReviewDao;
import com.yuliana.cafe.dao.impl.ReviewDaoImpl;
import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.ReviewService;

import java.util.ArrayList;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();
    private ReviewDao reviewDao = new ReviewDaoImpl();

    private ReviewServiceImpl(){}

    public static ReviewServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public void addReview(int userId, String header, String text, String rating) throws ServiceException {
        int reviewRating = Integer.parseInt(rating);
        Review review = new Review(header, text, reviewRating);
        try {
            reviewDao.addReview(review, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Review> findAllReviews() throws ServiceException{
        List<Review> reviews;
        try {
            reviews = reviewDao.findAllReviews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public List<Review> findReviewByHeader(String header) throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findReviewByHeader(header);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }
}
