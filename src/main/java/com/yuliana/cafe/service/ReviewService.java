package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface ReviewService {

    void addReview(int userId, String header, String text, String rating) throws ServiceException;
    List<Review> findAllReviews() throws ServiceException;
    List<Review> findReviewByHeader(String header) throws ServiceException;
    void deleteReview(int reviewId) throws ServiceException;
    void editReview(int reviewId, String header, String text, int rating) throws ServiceException;
    List<Review> findAllReviewsSortedByHeader(String header) throws ServiceException;

}
