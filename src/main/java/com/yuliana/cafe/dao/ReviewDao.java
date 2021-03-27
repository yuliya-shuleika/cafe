package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;

public interface ReviewDao {

    void addReview(Review review, int userId) throws DaoException;
    List<Review> findAllReviews() throws DaoException;
    List<Review> findReviewByHeader(String header) throws DaoException;
    void deleteReview(int reviewId) throws DaoException;
    void editReview(Review review) throws DaoException;
    List<Review> findAllReviewsSortedByHeader() throws DaoException;
}
