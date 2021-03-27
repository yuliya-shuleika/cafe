package com.yuliana.cafe.dao.impl;

import com.yuliana.cafe.connection.ConnectionPool;
import com.yuliana.cafe.dao.ReviewDao;
import com.yuliana.cafe.entity.Review;
import com.yuliana.cafe.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_REVIEW = "INSERT INTO reviews (user_id, header, text, rating) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_REVIEWS = "SELECT review_id, user_id, header, text, rating " +
            "FROM reviews";
    private static final String SELECT_REVIEWS_BY_HEADER = "SELECT review_id, user_id, header, text, rating " +
            "FROM reviews WHERE email COLLATE UTF8_GENERAL_CI LIKE ?";
    private static final String DELETE_REVIEW = "DELETE FROM reviews WHERE review_id = ?";
    private static final String UPDATE_REVIEW = "UPDATE reviews " +
            "SET header = ?, text = ?, rating = ? WHERE reviewId = ?";
    private static final String SELECT_ALL_DISHES_SORTED_BY_HEADER = "SELECT review_id, user_id, header, text, rating " +
            "FROM reviews ORDER BY header";

    @Override
    public void addReview(Review review, int userId) throws DaoException{
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW)){
            statement.setInt(1, userId);
            statement.setString(2, review.getHeader());
            statement.setString(3, review.getText());
            statement.setInt(4, review.getRating());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Review> findAllReviews() throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_REVIEWS);
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public List<Review> findReviewByHeader(String header) throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_REVIEWS_BY_HEADER)){
            String searchPattern = '%' + header + '%';
            statement.setString(1, searchPattern);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public void deleteReview(int reviewId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW)){
            statement.setInt(1, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void editReview(Review review) throws DaoException {
        Connection connection = pool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW)){
            statement.setString(1, review.getHeader());
            statement.setString(2, review.getText());
            statement.setInt(3, review.getRating());
            statement.setInt(4, review.getReviewId());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Review> findAllReviewsSortedByHeader() throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES_SORTED_BY_HEADER);
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    private Review createReview(ResultSet userData) throws SQLException{
        int reviewId = userData.getInt(1);
        String header = userData.getString(2);
        String text = userData.getString(3);
        int rating = userData.getInt(4);
        Review review = new Review(reviewId, header, text, rating);
        return review;
    }


}
