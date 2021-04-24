package com.yuliana.cafe.model.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Base dao interface with default methods
 *
 * @author Yulia Shuleiko
 */
public interface BaseDao {

    /**
     * Commit the transaction.
     *
     * @param connection database connection
     */
    default void commit(Connection connection) {
        final Logger logger = LogManager.getLogger();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

    /**
     * Rollback the transaction.
     *
     * @param connection database connection
     */
    default void rollback(Connection connection) {
        final Logger logger = LogManager.getLogger();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

    /**
     * Set auto commit to the one of two values: true or false.
     *
     * @param connection database connection
     * @param autoCommit <code>true</code> if auto commit must be set otherwise <code>false</code>
     */
    default void setAutoCommit(Connection connection, boolean autoCommit) {
        final Logger logger = LogManager.getLogger();
        if (connection != null) {
            try {
                connection.setAutoCommit(autoCommit);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

    /**
     * Close database statement.
     *
     * @param statement database connection
     */
    default void close(Statement statement) {
        final Logger logger = LogManager.getLogger();
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Statement was`t closed.", e);
            }
        }
    }
}
