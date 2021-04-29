package com.yuliana.cafe.model.service;

import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.ReviewStatus;
import com.yuliana.cafe.model.entity.User;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * UserServise interface provides operations with {@code PromoCode} objects and related to it.
 *
 * @author Yulia Shuleiko
 */
public interface ReviewService {

    /**
     *
     * @param userId id of the user
     * @param reviewFields map of the string.
     *                     The key represents field of the form and the value is the user's input
     * @param rating rating of the review. It's a number between 1 and 5
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void addReview(int userId, Map<String, String> reviewFields,
                   String rating) throws ServiceException;

    /**
     * Find all the reviews.
     *
     * @return list of the reviews
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Review> findAllReviews() throws ServiceException;

    /**
     * Find review by the part of it's header. Validate the input.
     *
     * @param header part of the header of the review
     * @return list of the reviews
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Review> findReviewByHeader(String header) throws ServiceException;

    /**
     * Delete the review by it's id.
     *
     * @param reviewId id of the review
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void deleteReview(int reviewId) throws ServiceException;

    void editReview(int reviewId, Map<String, String> reviewFields,
                    int rating, String reviewStatus) throws ServiceException;

    /**
     * Find all the reviews sorted by header.
     *
     * @return list of the reviews
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Review> findAllReviewsSortedByHeader() throws ServiceException;

    /**
     *
     * @param status
     * @return
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    List<Review> findReviewsByStatus(ReviewStatus status) throws ServiceException;

    /**
     * Find the review by it's id.
     *
     * @param reviewId id of the review
     * @return the {@code Review} object
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Optional<Review> findReviewById(int reviewId) throws ServiceException;

    /**
     * Update status of the review to one of this: new, approved or rejected
     *
     * @param status the {@code ReviewStatus} object
     * @param reviewId id of the review
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    void updateStatus(String status, int reviewId) throws ServiceException;

    /**
     * Find all the reviews that have status 'approved' and it's authors.
     *
     * @return map of the {@code Review} and {@code User}
     * @throws ServiceException if the {@code DaoException} was thrown
     */
    Map<Review, User> findApprovedReviewsWithAuthors() throws ServiceException;

}
