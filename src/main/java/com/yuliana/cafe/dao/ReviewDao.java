package com.yuliana.cafe.dao;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.DaoException;

import java.util.List;

public interface ReviewDao {
    void addReview(Review review, int userId) throws DaoException;
    List<Review> getAllReviews() throws DaoException;
}
