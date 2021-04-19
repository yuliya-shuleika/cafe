package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.entity.ReviewStatus;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReviewService {

    void addReview(int userId, Map<String, String> reviewFields,
                   String rating) throws ServiceException;

    List<Review> findAllReviews() throws ServiceException;

    List<Review> findReviewByHeader(String header) throws ServiceException;

    void deleteReview(int reviewId) throws ServiceException;

    void editReview(int reviewId, Map<String, String> reviewFields,
                    int rating, String reviewStatus) throws ServiceException;

    List<Review> findAllReviewsSortedByHeader() throws ServiceException;

    List<Review> findReviewsByStatus(ReviewStatus status) throws ServiceException;

    Optional<Review> findReviewById(int reviewId) throws ServiceException;

    List<Review> findAllUserReviews(int userId) throws ServiceException;

    void updateStatus(String status, int reviewId) throws ServiceException;

    Map<Review, User> findApprovedReviewsWithAuthors() throws ServiceException;

}
