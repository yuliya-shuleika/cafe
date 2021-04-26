package com.yuliana.cafe.exception;

/**
 * An exception that thrown when errors with access to database occur.
 *
 * @author Yulia Shuleiko
 */
public class DaoException extends Exception {

    /**
     * Constructs a {@code DaoException} object with a given message.
     *
     * @param message message with information about error
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code DaoException} object with a given cause.
     *
     * @param cause cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
