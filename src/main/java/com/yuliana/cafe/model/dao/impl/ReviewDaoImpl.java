package com.yuliana.cafe.model.dao.impl;

import com.yuliana.cafe.model.connection.ConnectionPool;
import com.yuliana.cafe.model.dao.ReviewDao;
import com.yuliana.cafe.model.entity.*;
import com.yuliana.cafe.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl implements ReviewDao {

    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.INSTANCE;
    private static final String INSERT_REVIEW = "INSERT INTO reviews (user_id, header, text, rating, status) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_REVIEWS = "SELECT review_id, header, text, rating, status " +
            "FROM reviews";
    private static final String SELECT_REVIEWS_BY_HEADER = "SELECT review_id, header, text, rating, status " +
            "FROM reviews WHERE email COLLATE UTF8_GENERAL_CI LIKE ?";
    private static final String DELETE_REVIEW = "DELETE FROM reviews WHERE review_id = ?";
    private static final String UPDATE_REVIEW = "UPDATE reviews " +
            "SET header = ?, text = ?, rating = ?, status = ? WHERE reviewId = ?";
    private static final String UPDATE_STATUS = "UPDATE reviews SET status = ? WHERE review_id = ?";
    private static final String SELECT_ALL_DISHES_SORTED_BY_HEADER = "SELECT review_id, header, text, rating, status " +
            "FROM reviews ORDER BY header";
    private static final String SELECT_REVIEWS_BY_STATUS = "SELECT review_id, header, text, rating, status " +
            "FROM reviews WHERE status = ?";
    private static final String SELECT_USER_REVIEWS = "SELECT review_id, header, text, rating, status " +
            "FROM reviews WHERE user_id = ?";
    private static final String SELECT_REVIEWS_BY_ID = "SELECT review_id, header, text, rating, status " +
            "FROM reviews WHERE review_id = ?";
    private static final String SELECT_USER_BY_REVIEWS_ID = "SELECT users.user_id, users.name, " +
            "users.email, users.role, users.status, users.avatar FROM reviews " +
            "JOIN users ON users.user_id = reviews.user_id " +
            "WHERE reviews.review_id = ?";

    @Override
    public void addReview(Review review, int userId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW)) {
            statement.setInt(1, userId);
            statement.setString(2, review.getHeader());
            statement.setString(3, review.getText());
            statement.setInt(4, review.getRating());
            ReviewStatus reviewStatus = review.getStatus();
            String status = reviewStatus.name().toLowerCase();
            statement.setString(5, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Review> findAllReviews() throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_REVIEWS);
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public List<Review> findReviewByHeader(String header) throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_REVIEWS_BY_HEADER)) {
            String searchPattern = '%' + header + '%';
            statement.setString(1, searchPattern);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public void deleteReview(int reviewId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW)) {
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
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW)) {
            statement.setString(1, review.getHeader());
            statement.setString(2, review.getText());
            statement.setInt(3, review.getRating());
            statement.setInt(4, review.getReviewId());
            ReviewStatus reviewStatus = review.getStatus();
            String status = reviewStatus.name().toLowerCase();
            statement.setString(5, status);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public void updateStatus(ReviewStatus status, int reviewId) throws DaoException {
        Connection connection = pool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            String reviewStatus = status.name().toLowerCase();
            statement.setString(1, reviewStatus);
            statement.setInt(2, reviewId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
    }

    @Override
    public List<Review> findAllReviewsSortedByHeader() throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SELECT_ALL_DISHES_SORTED_BY_HEADER);
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public List<Review> findReviewsByStatus(ReviewStatus status) throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_REVIEWS_BY_STATUS)) {
            String reviewStatus = status.name().toLowerCase();
            statement.setString(1, reviewStatus);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public Optional<Review> findReviewById(int reviewId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<Review> reviewOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_REVIEWS_BY_ID)) {
            statement.setInt(1, reviewId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Review review = createReview(result);
                reviewOptional = Optional.of(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviewOptional;
    }

    @Override
    public List<Review> findAllUserReviews(int userId) throws DaoException {
        Connection connection = pool.getConnection();
        List<Review> reviews = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_REVIEWS)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Review review = createReview(result);
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return reviews;
    }

    @Override
    public Optional<User> findUserByReviewId(int reviewId) throws DaoException {
        Connection connection = pool.getConnection();
        Optional<User> userOptional = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_REVIEWS_ID)) {
            statement.setInt(1, reviewId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int userId = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                String userRole = result.getString(4);
                UserRole role = UserRole.valueOf(userRole.toUpperCase());
                String userStatus = result.getString(5);
                UserStatus status = UserStatus.valueOf(userStatus.toUpperCase());
                String avatar = result.getString(6);
                User user = new User(userId, name, email, role, status, avatar);
                userOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            pool.releaseConnection(connection);
        }
        return userOptional;
    }

    private Review createReview(ResultSet userData) throws SQLException {
        int reviewId = userData.getInt(1);
        String header = userData.getString(2);
        String text = userData.getString(3);
        int rating = userData.getInt(4);
        String reviewStatus = userData.getString(5);
        ReviewStatus status = ReviewStatus.valueOf(reviewStatus.toUpperCase());
        Review review = new Review(reviewId, header, text, rating, status);
        return review;
    }


}
