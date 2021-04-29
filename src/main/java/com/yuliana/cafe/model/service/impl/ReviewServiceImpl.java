package com.yuliana.cafe.model.service.impl;

import com.yuliana.cafe.model.dao.ReviewDao;
import com.yuliana.cafe.model.dao.impl.ReviewDaoImpl;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.ReviewStatus;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.exception.ServiceException;
import com.yuliana.cafe.model.service.ReviewService;
import com.yuliana.cafe.model.service.validator.ReviewValidator;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Implementation of the {@code ReviewService} interface.
 *
 * @author Yulia Shuleiko
 */
public class ReviewServiceImpl implements ReviewService {

    private static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();
    private ReviewDao reviewDao = ReviewDaoImpl.getInstance();
    private static final String FIELD_REVIEW_HEADER = "review_header";
    private static final String FIELD_REVIEW_TEXT = "review_text";

    /**
     * Forbid creation of the new objects of the class.
     */
    private ReviewServiceImpl() {}

    /**
     * Getter method of the instance of the {@code ReviewServiceImpl} class.
     *
     * @return the {@code AddressDaoImpl} object
     */
    public static ReviewServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void addReview(int userId, Map<String, String> reviewFields, String rating) throws ServiceException {
        boolean isValid = ReviewValidator.isValidReviewForm(reviewFields);
        if (isValid) {
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
    public List<Review> findAllReviews() throws ServiceException {
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
        List<Review> reviews = new ArrayList<>();
        boolean isValid = ReviewValidator.isValidReviewSearch(header);
        if (isValid) {
            try {
                reviews = reviewDao.findReviewByHeader(header);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
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
        if (isValid) {
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
    public List<Review> findReviewsByStatus(ReviewStatus status) throws ServiceException {
        List<Review> reviews;
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
    public void updateStatus(String status, int reviewId) throws ServiceException {
        ReviewStatus reviewStatus = ReviewStatus.valueOf(status.toUpperCase());
        try {
            reviewDao.updateStatus(reviewStatus, reviewId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Review, User> findApprovedReviewsWithAuthors() throws ServiceException {
        List<Review> reviews;
        try {
            reviews = reviewDao.findReviewsByStatus(ReviewStatus.APPROVED);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Map<Review, User> reviewsWithAuthors = new HashMap<>();
        for (Review review : reviews) {
            int reviewId = review.getReviewId();
            try {
                Optional<User> userOptional = reviewDao.findUserByReviewId(reviewId);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    reviewsWithAuthors.put(review, user);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return reviewsWithAuthors;
    }

}
