package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.entity.ReviewStatus;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface ReviewDao defines operations with dishes table
 *
 * @author Yulia Shuleiko
 */
public interface ReviewDao extends BaseDao {

    /**
     * Add new review that was written by user.
     *
     * @param review the {@code Review} object
     * @param userId id of the user that added review
     * @throws DaoException is thrown when occurred error with access to database
     */
    void addReview(Review review, int userId) throws DaoException;

    /**
     * Find all the reviews.
     *
     * @return list of the reviews
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Review> findAllReviews() throws DaoException;

    /**
     * Find the reviews by the part of the header. Case-insensitive.
     *
     * @param header part of the header of the review
     * @return list of the reviews
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Review> findReviewByHeader(String header) throws DaoException;

    /**
     * Delete review by id.
     *
     * @param reviewId id of the review
     * @throws DaoException is thrown when occurred error with access to database
     */
    void deleteReview(int reviewId) throws DaoException;

    /**
     * Edit the review.
     *
     * @param review the {@code Review} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    void editReview(Review review) throws DaoException;

    /**
     * Update status of the review (new, approved or rejected)
     *
     * @param status the {@code ReviewStatus} object, status of the review
     * @param reviewId id of the review
     * @throws DaoException is thrown when occurred error with access to database
     */
    void updateStatus(ReviewStatus status, int reviewId) throws DaoException;

    /**
     * Find the reviews ordered by header.
     *
     * @return list of the reviews
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Review> findAllReviewsSortedByHeader() throws DaoException;

    /**
     * Find the reviews that have certain status.
     *
     * @param status status the {@code ReviewStatus} object, status of the review
     * @return list of the reviews
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Review> findReviewsByStatus(ReviewStatus status) throws DaoException;

    /**
     * Find review by id.
     *
     * @param reviewId id of the review
     * @return the {@code Review} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<Review> findReviewById(int reviewId) throws DaoException;

    /**
     * Find the reviews written by user.
     *
     * @param userId id of the user
     * @return list of the reviews
     * @throws DaoException is thrown when occurred error with access to database
     */
    List<Review> findAllUserReviews(int userId) throws DaoException;

    /**
     * Find the author of the review.
     *
     * @param reviewId id of the review
     * @return the {@code User} object
     * @throws DaoException is thrown when occurred error with access to database
     */
    Optional<User> findUserByReviewId(int reviewId) throws DaoException;
}
