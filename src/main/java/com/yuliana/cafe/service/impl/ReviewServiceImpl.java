package com.yuliana.cafe.service.impl;

import com.yuliana.cafe.dao.ReviewDao;
import com.yuliana.cafe.dao.impl.ReviewDaoImpl;
import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.entity.ReviewStatus;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.service.ReviewService;
import com.yuliana.cafe.service.validator.ReviewValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();
    private ReviewDao reviewDao = new ReviewDaoImpl();
    private static final String FIELD_REVIEW_HEADER = "review_header";
    private static final String FIELD_REVIEW_TEXT = "review_text";

    private ReviewServiceImpl(){}

    public static ReviewServiceImpl getInstance(){
        return INSTANCE;
    }

    @Override
    public void addReview(int userId, Map<String, String> reviewFields, String rating) throws ServiceException {
        boolean isValid = ReviewValidator.isValidReviewForm(reviewFields);
        if(isValid) {
            int reviewRating = Integer.parseInt(rating);
            ReviewStatus status = ReviewStatus.NEW;
            String header = reviewFields.get(FIELD_REVIEW_HEADER);
            String text = reviewFields.get(FIELD_REVIEW_TEXT);
            Review review = new Review(header, text, reviewRating, status);
            try {
                reviewDao.addReview(review, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
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

    @Override
    public void deleteReview(int reviewId) throws ServiceException {
        try {
            reviewDao.deleteReview(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void editReview(int reviewId, Map<String, String> reviewFields,
                           int rating, String reviewStatus) throws ServiceException {
        boolean isValid = ReviewValidator.isValidReviewForm(reviewFields);
        if(isValid) {
            ReviewStatus status = ReviewStatus.valueOf(reviewStatus.toUpperCase());
            String header = reviewFields.get(FIELD_REVIEW_HEADER);
            String text = reviewFields.get(FIELD_REVIEW_TEXT);
            Review review = new Review(reviewId, header, text, rating, status);
            try {
                reviewDao.editReview(review);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }

    @Override
    public List<Review> findAllReviewsSortedByHeader() throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findAllReviewsSortedByHeader();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public List<Review> findReviewsByStatus(String reviewStatus) throws ServiceException {
        List<Review> reviews;
        ReviewStatus status = ReviewStatus.valueOf(reviewStatus.toUpperCase());
        try {
            reviews = reviewDao.findReviewsByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findReviewById(int reviewId) throws ServiceException {
        Optional<Review> reviewOptional;
        try {
            reviewOptional = reviewDao.findReviewById(reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviewOptional;
    }

    @Override
    public List<Review> findAllUserReviews(int userId) throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findAllUserReviews(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviews;
    }

}
