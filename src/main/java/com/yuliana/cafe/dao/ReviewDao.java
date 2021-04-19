package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.entity.ReviewStatus;
import com.yuliana.cafe.entity.User;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface ReviewDao extends BaseDao {

    void addReview(Review review, int userId) throws DaoException;

    List<Review> findAllReviews() throws DaoException;

    List<Review> findReviewByHeader(String header) throws DaoException;

    void deleteReview(int reviewId) throws DaoException;

    void editReview(Review review) throws DaoException;

    void updateStatus(ReviewStatus status, int reviewId) throws DaoException;

    List<Review> findAllReviewsSortedByHeader() throws DaoException;

    List<Review> findReviewsByStatus(ReviewStatus status) throws DaoException;

    Optional<Review> findReviewById(int reviewId) throws DaoException;

    List<Review> findAllUserReviews(int userId) throws DaoException;

    Optional<User> findUserByReviewId(int reviewId) throws DaoException;
}
