package com.yuliana.cafe.service;

import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.ServiceException;

import java.util.List;

public interface ReviewService {

    void addReview(int userId, String header, String text, String rating) throws ServiceException;
    List<Review> getAllReviews() throws ServiceException;

}
