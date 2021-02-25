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

    private ReviewDao reviewDao = new ReviewDaoImpl();

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
    public List<Review> getAllReviews() throws ServiceException{
        List<Review> reviews = new ArrayList<>();
        try {
            reviews = reviewDao.getAllReviews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }
}
