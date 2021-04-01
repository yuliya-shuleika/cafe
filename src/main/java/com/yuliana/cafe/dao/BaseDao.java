package com.yuliana.cafe.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao {

    default void commit(Connection connection) {
        final Logger logger = LogManager.getLogger();
        if(connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

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
