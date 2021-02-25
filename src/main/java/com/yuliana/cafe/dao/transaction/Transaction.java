package com.yuliana.cafe.dao.transaction;

import com.yuliana.cafe.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    private Connection connection;

    public Transaction(Connection connection){
        this.connection = connection;
    }

    public void setAutoCommit(boolean autoCommit) throws DaoException{
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new DaoException();
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }

    public void close() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
