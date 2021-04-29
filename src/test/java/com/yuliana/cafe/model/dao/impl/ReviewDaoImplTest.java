package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.exception.DaoException;
import com.yuliana.cafe.model.dao.ReviewDao;
import com.yuliana.cafe.model.entity.Review;
import com.yuliana.cafe.model.entity.ReviewStatus;
import com.yuliana.cafe.model.entity.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
public class ReviewDaoImplTest {

    private ReviewDao reviewDao;
    private int reviewId;
    private String headerPart;

    @BeforeTest
    public void init() {
        reviewDao = ReviewDaoImpl.getInstance();
        reviewId = 5;
        headerPart = "good";
    }

    @Test
    public void findAllReviewsTest() throws DaoException {
        List<Review> reviews = reviewDao.findAllReviews();
        int size = reviews.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findAllReviewsSortedByHeaderTest() throws DaoException {
        List<Review> reviews = reviewDao.findAllReviewsSortedByHeader();
        int size = reviews.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findReviewByIdTest() throws DaoException {
        Optional<Review> reviewOptional = reviewDao.findReviewById(reviewId);
        Assert.assertNotEquals(reviewOptional, Optional.empty());
    }

    @Test
    public void findReviewByHeaderTest() throws DaoException {
        List<Review> reviews = reviewDao.findReviewByHeader(headerPart);
        int size = reviews.size();
        Assert.assertNotEquals(size, 0);
    }

    @Test
    public void findReviewsByStatusTest() throws DaoException {
        List<Review> reviews = reviewDao.findReviewsByStatus(ReviewStatus.REJECTED);
        int size = reviews.size();
        Assert.assertEquals(size, 0);
    }

    @Test
    public void findUserByReviewIdTest() throws DaoException {
        Optional<User> userOptional = reviewDao.findUserByReviewId(reviewId);
        Assert.assertNotEquals(userOptional, Optional.empty());
    }
}
